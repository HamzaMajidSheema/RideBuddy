package com.easysawari.ridebuddy.Fragments;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.easysawari.ridebuddy.JSON.RouteObject;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.easysawari.ridebuddy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class SearchFragment extends Fragment implements OnMapReadyCallback {

    static List<RouteObject> routes;
    ArrayList<String> places;
    static int position=0;
    GoogleMap mMap;
    SupportMapFragment sf;
    SearchView source,destination;
    ListView prediction;
    String startLat,startLong,endLat,endLong;
    View rootView;
    ArrayAdapter<String> adapter;
    String start,end;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initilizeMap();

        source = (SearchView) view.findViewById(R.id.source);
        destination = (SearchView) view.findViewById(R.id.destination);
        prediction = (ListView) view.findViewById(R.id.prediction);

        start=" ";
        end=" ";

        source.setQueryHint("Source");
        destination.setQueryHint("Destination");

        //SearchActivity.navigation=1;

        destination.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                prediction.setVisibility(View.INVISIBLE);
                end = query;

                if(!start.equals(" "))
                    searchRoute();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {

                    if (places != null)
                        places.clear();

                    getSugestion(newText);





                    {
                        prediction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                adapter.notifyDataSetChanged();
                                String text=places.get(position);
                                text = (String) text.subSequence(0,text.length()-10);
                                destination.setQuery(text, true);
                            }
                        });
                    }

                }
                return false;
            }
        });

        source.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                prediction.setVisibility(View.INVISIBLE);
                start = query;

                if(!end.equals(" "))
                    searchRoute();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty()) {

                    if (places != null)
                        places.clear();

                    getSugestion(newText);
                    {
                        prediction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                adapter.notifyDataSetChanged();
                                String text=places.get(position);
                                text = (String) text.subSequence(0,text.length()-10);
                                source.setQuery(text, true);
                            }
                        });
                    }

                }
                return false;
            }
        });

        // Inflate the layout for this fragment
        return view;


    }

    private void initilizeMap() {
        sf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.searchMap);
        if (sf == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            sf = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, sf).commit();
        }

        sf.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(33.521513, 73.090899)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(24));
    }

/*
    public void loadFragment(Fragment fragment) {
        // load fragment

        FragmentTransaction transaction =  getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    */

    public void searchRoute()
    {
        //start.replaceAll(",","");

        String[]  origin=start.split(" ");
        String[]  destination=end.split(" ");

        start="";
        for (int i=0;i<origin.length;i++)
        {
            if(i==0)
                start=start+origin[i];
            else
                start=start+"+"+origin[i];
        }

        end="";
        for (int i=0;i<destination.length;i++)
        {
            if(i==0)
                end=end+destination[i];
            else
                end=end+"+"+destination[i];
        }



        String url = "https://maps.googleapis.com/maps/api/directions/json?key=AIzaSyA-QoUrBCw1eZxMWkCRbmwDQs9K2Tk0vto&origin="+start+"&destination="+end;

        Log.i(TAG, "searchRoute: "+ url);

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            if(response.getString("status").equals("OK")) {
                                JSONArray r = response.getJSONArray("routes");
                                JSONArray legs = r.getJSONObject(0).getJSONArray("legs");
                                startLat = legs.getJSONObject(0).getJSONObject("start_location").getString("lat");
                                startLong = legs.getJSONObject(0).getJSONObject("start_location").getString("lng");
                                endLat = legs.getJSONObject(0).getJSONObject("end_location").getString("lat");
                                endLong = legs.getJSONObject(0).getJSONObject("end_location").getString("lng");
                                routes = getRoutes();
                            }
                            else
                            {
                                prediction.setVisibility(View.INVISIBLE);
                                prediction.setBackgroundColor(Color.TRANSPARENT);
                                Toast.makeText(getContext(), "No route Found", Toast.LENGTH_SHORT).show();

                            }


                        }catch (JSONException e)
                        {
                            Log.e(TAG, "onResponse: ",e );
                        }


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        });
        // Adding JsonObject request to request queue
        Volley.newRequestQueue(this.getContext()).add(jsonObjectReq);
    }

    List<RouteObject> getRoutes()
    {
        final List<RouteObject> arr=new ArrayList<RouteObject>();
        String url = "https://exaride-webservices.herokuapp.com/api/users/routes";

        Map<String,String> params=new HashMap<String, String>();
        params.put("startLat",startLat);
        params.put("startLong",startLong);
        params.put("endLat",endLat);
        params.put("endLong",endLong);

        JSONObject jsonObject =new JSONObject(params);

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response.getString("status").equals("success"))
                            {
                                JSONArray object=response.getJSONArray("object");
                                for (int i=0;i<object.length();i++)
                                {
                                    RouteObject r=new RouteObject();
                                    r.setTitle(object.getJSONObject(i).getString("title"));
                                    r.setRouteId(object.getJSONObject(i).getString("id"));
                                    r.setStartLoc(object.getJSONObject(i).getJSONArray("startLoc"));
                                    r.setEndLoc(object.getJSONObject(i).getJSONArray("endLoc"));
                                    r.setStopList(object.getJSONObject(i).getJSONArray("listOfStops"));
                                    r.setTime("9:00am to 3:00am");
                                    r.setCarType("Corolla "+i);
                                    arr.add(r);
                                }

                               // loadFragment(new RouteFragment());

                            }

                        }catch (JSONException e)
                        {
                            Log.e(TAG, "onResponse: ", e );
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        });
        // Adding JsonObject request to request queue
        Volley.newRequestQueue(this.getContext()).add(jsonObjectReq);
        return arr;
    }

    void getSugestion(String input)
    {
        String[] query=input.split(" ");

        prediction.setEnabled(false);
        input="";
        for (int i=0;i<query.length;i++)
        {
            if(i==0)
                input=query[i];
            else
                input=input+"+"+query[i];
        }

        String url = "https://maps.googleapis.com/maps/api/place/queryautocomplete/json?key=AIzaSyAzDk8-rmev9vDHJTiMXH53qZsp62EuzRc&input="+input+"+pakistan";

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response.getString("status").equals("OK"))
                            {
                                places=new ArrayList<String>();
                                JSONArray pred=response.getJSONArray("predictions");

                                for(int i=0;i<pred.length();i++)
                                {
                                    String obj=pred.getJSONObject(0).getString("description");
                                    places.add(obj);
                                }

                                try {

                                    adapter =new  ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,places);
                                    prediction.setAdapter(adapter);
                                    prediction.setVisibility(View.VISIBLE);
                                    prediction.setEnabled(true);

                                } catch (IllegalStateException e) {
                                    e.printStackTrace();
                                }
                            }

                        }catch(JSONException e)
                        {
                            Log.e(TAG, "onResponse: ",e );
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        });
        // Adding JsonObject request to request queue
        Volley.newRequestQueue(getContext()).add(jsonObjectReq);
    }


}
