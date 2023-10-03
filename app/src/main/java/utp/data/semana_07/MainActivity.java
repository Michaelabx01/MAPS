package utp.data.semana_07;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMapLoadedCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, OnMapReadyCallback {

    private EditText txtLatitud, txtLongitud;
    private GoogleMap miMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asigna los EditText
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));
        miMap.clear();

        LatLng ubicacion = new LatLng(latLng.latitude, latLng.longitude);
        miMap.addMarker(new MarkerOptions().position(ubicacion).title("UTP").icon(getResizedMarkerIcon(R.drawable.ic_goku, 200, 200)));  // Utiliza el icono personalizado y establece el tamaño
        miMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        miMap = googleMap;
        miMap.setOnMapClickListener(this);
        miMap.setOnMapLongClickListener(this);

        // Ubicación original
        LatLng ubicacion = new LatLng(-11.983552248496029, -77.00982822624428);


        miMap.addMarker(new MarkerOptions().position(ubicacion).title("UTP").icon(getResizedMarkerIcon(R.drawable.ic_goku, 200, 200)));  // Utiliza el icono personalizado y establece el tamaño
        miMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));

        // Nuevo Marcador
        agregarMarcador(-11.981328063458886, -77.00560333144266, "Nuevo Marcador");

        // Coordenadas de la línea
        List<LatLng> coordenadasLinea = new ArrayList<>();
        coordenadasLinea.add(new LatLng(-11.98356535050792, -77.00984680840234));
        coordenadasLinea.add(new LatLng(-11.984130104205315, -77.00836040737599));
        coordenadasLinea.add(new LatLng(-11.98475101989811, -77.00679107397495));
        coordenadasLinea.add(new LatLng(-11.984616852308303, -77.00675917695722));
        coordenadasLinea.add(new LatLng(-11.98432355548437, -77.00668581381643));
        coordenadasLinea.add(new LatLng(-11.983839927033545, -77.00647210371595));
        coordenadasLinea.add(new LatLng(-11.983300047919116, -77.00627316665538));
        coordenadasLinea.add(new LatLng(-11.982270382429055, -77.00591273035498));
        coordenadasLinea.add(new LatLng(-11.981331199269475, -77.00561608809005));

        // Dibuja la línea
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(coordenadasLinea)
                .color(getResources().getColor(R.color.colorPolyline))
                .width(10);

        miMap.addPolyline(polylineOptions);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));
        miMap.clear();

        LatLng ubicacion = new LatLng(latLng.latitude, latLng.longitude);
        miMap.addMarker(new MarkerOptions().position(ubicacion).title("").icon(getResizedMarkerIcon(R.drawable.ic_goku, 150, 150)));  // Utiliza el icono personalizado y establece el tamaño
        miMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
    }

    @Override
    public void onMapLoaded() {

    }

    private BitmapDescriptor getResizedMarkerIcon(int resourceId, int width, int height) {

        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return BitmapDescriptorFactory.fromBitmap(resizedBitmap);
    }

    private void agregarMarcador(double latitud, double longitud, String titulo) {
        LatLng ubicacion = new LatLng(latitud, longitud);
        miMap.addMarker(new MarkerOptions()
                .position(ubicacion)
                .title(titulo)
                .icon(getResizedMarkerIcon(R.drawable.ic_goku, 150, 150)));
        miMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
    }
}
