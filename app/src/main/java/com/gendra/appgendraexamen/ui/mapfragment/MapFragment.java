package com.gendra.appgendraexamen.ui.mapfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.gendra.appgendraexamen.R;
import com.gendra.appgendraexamen.application.AppInit;
import com.gendra.appgendraexamen.application.BaseFragment;
import com.gendra.appgendraexamen.dagger.modules.RepositoryModule;
import com.gendra.appgendraexamen.data.model.FormDescCorrdinates;
import com.gendra.appgendraexamen.databinding.FragmentMapBinding;
import com.gendra.appgendraexamen.ui.MainActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MapFragment extends BaseFragment implements MapContract.View,
        OnMapReadyCallback,GoogleMap.OnMapLoadedCallback{

    public static final String TAG = MapFragment.class.getSimpleName();

    //Biding
    private FragmentMapBinding mFragmentMapBinding;
    private GoogleMap mMap;
    private Polygon mutablePolygon;
    private MainActivity mActivity;
    private List<MarkerOptions> markers;

    private View view;

    @Inject
    MapContract.Presenter presenter;

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayout(), container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapCoordinates);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragmentMapBinding = FragmentMapBinding.bind(view);

        mFragmentMapBinding.txtInputEtCdPostale.addTextChangedListener(getWatchTetxViewCdPostal(mFragmentMapBinding.txtInputEtCdPostale));
        mFragmentMapBinding.txtInputEtCdPostale.setOnEditorActionListener(new TextInputEditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    emptyPanelInfoAndMap();
                    hideSoftKeyboard(getContext());
                    presenter.getCoordinatesPolygon(mFragmentMapBinding.txtInputEtCdPostale.getText().toString());
                    return true;
                }
                return false;
            }
        });

        mActivity.goneImageLogo();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mActivity = (MainActivity) context;
        }
    }

    @Override
    public void injectDependencies() {
        super.injectDependencies();

        DaggerMapComponent.builder()
                .publicSessionComponent(AppInit.getAppComponent().getPublicMSSessionManager().getPublicMSComponent())
                .mapModule(new MapModule(this))
                .repositoryModule(new RepositoryModule())
                .build()
                .inject(this);

    }

    @Override
    public void showSnackBar(String message) {
        mActivity.showSnackBar(message);
    }

    @Override
    public void buildPolygons(List<List<List<Double>>> coordinates){
        mMap.clear();
        mMap.setContentDescription("Demostracion para mostrar poligonos en mapa");
        List<LatLng> listLatLog = createPolygon(coordinates);
        int shadeColor=0x44ff0000;
        mutablePolygon = mMap.addPolygon(new PolygonOptions()
                .addAll(listLatLog)
                .addHole(listLatLog)
                .addHole(listLatLog)
                .fillColor(shadeColor)
                .strokeColor(R.color.colorAccent)
                .strokeWidth(15));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng latLng : listLatLog) {
            builder.include(latLng);
        }

        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 130);
        mMap.animateCamera(cu);


        mMap.setOnMapLoadedCallback(this);
        mutablePolygon.setStrokeJointType(JointType.BEVEL);
        mutablePolygon.setStrokePattern(null);

        presenter.getDescCoordinatesPolygon(mFragmentMapBinding.txtInputEtCdPostale.getText().toString());

    }

    @Override
    public void showDescPolygonCoordinates(FormDescCorrdinates responseDesc) {
        mFragmentMapBinding.txtInEtCountry.setText(responseDesc.getCountry());
        mFragmentMapBinding.txtInputEtEntity.setText(responseDesc.getEntity());
        mFragmentMapBinding.txtInEtCity.setText(responseDesc.getCity());
        mFragmentMapBinding.txtInputEtMunicipality.setText(responseDesc.getMunicipality());
        mFragmentMapBinding.txtInEtColony.setText(responseDesc.getColony());
    }

    public void emptyPanelInfoAndMap(){
        mFragmentMapBinding.txtInEtCountry.setText("");
        mFragmentMapBinding.txtInputEtEntity.setText("");
        mFragmentMapBinding.txtInEtCity.setText("");
        mFragmentMapBinding.txtInputEtMunicipality.setText("");
        mFragmentMapBinding.txtInEtColony.setText("");
        mMap.clear();
    }

    private List<LatLng> createPolygon(List<List<List<Double>>> coordinates) {

        List<LatLng> result = new ArrayList<>();
        BitmapDescriptor bitmapDescriptor = bitmapDescriptorFromVector(getContext(),R.drawable.ic_marker_3_12);
        int i;
        int sizeI = coordinates.size();
        for(i=0; i<sizeI; i++){

            List<List<Double>> listCoo = coordinates.get(i);
            int j=0;
            int sizeJ = listCoo.size();

            for(j=0; j<sizeJ; j++){
                List<Double> listCoodF=listCoo.get(j);
                MarkerOptions markerOpt = new MarkerOptions();
                LatLng latlng = new LatLng(listCoodF.get(1), listCoodF.get(0));
                result.add(latlng);
                markerOpt.icon(bitmapDescriptor);
                markerOpt.position(latlng);
                markers.add(markerOpt);
                mMap.addMarker(markerOpt);// se agregan los marcadores
            }

        }

        return result;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        this.mMap.getUiSettings().setZoomControlsEnabled(false);
        this.mMap.getUiSettings().setMapToolbarEnabled(false);
        this.mMap.getUiSettings().setMyLocationButtonEnabled(false);

        this.mMap.setContentDescription("Ejemplo poligons");

        markers = new ArrayList<MarkerOptions>();
    }

    @Override
    public void onMapLoaded() {

    }

    public TextWatcher getWatchTetxViewCdPostal(EditText mEmail){

        return new  TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s==null)return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s==null)return;
            }

            @Override
            public void afterTextChanged(Editable s) {
               String numTotal= presenter.validNumCdPostal(s.toString());
                mFragmentMapBinding.tvNumCdPostal.setText(numTotal);
            }
        };
    }

}