package com.svalero.fitstagerapplication.api;



import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Activity_Client;
import com.svalero.fitstagerapplication.domain.Client;
import com.svalero.fitstagerapplication.domain.Gym;
import com.svalero.fitstagerapplication.domain.Staff;
import com.svalero.fitstagerapplication.domain.dto.ActivityDTO;
import com.svalero.fitstagerapplication.domain.dto.Activity_ClientDTO;
import com.svalero.fitstagerapplication.domain.dto.ClientDTO;
import com.svalero.fitstagerapplication.domain.dto.StaffDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FitStagerApiInterface {


    //GYMS
    @GET("gyms")
    Call<List<Gym>> getGyms();

    @GET("gyms")
    Call<List<Gym>> getGymsByName(@Query("name") String name);

    @GET("gyms")
    Call<List<Gym>> getGymsByCity(@Query("city") String city);

    @GET("gyms")
    Call<List<Gym>> getGymsByStreet(@Query("street") String street);

    @POST("gyms")
    Call<Gym> addGym(@Body Gym gym);

    @PUT("gym/{id}")
    Call<Gym> modifyGym(@Path("id") long id, @Body Gym gym);


    @DELETE("gym/{id}")
    Call<Void> deleteGym(@Path("id") long id);



    //STAFFS

    @GET("staffs")
    Call<List<Staff>> getStaffs();

    @POST("staffs")
    Call<Staff> addStaff(@Body StaffDTO staffDTO);

    @DELETE("staff/{id}")
    Call<Void> deleteStaff(@Path("id") long id);

    @PUT("staff/{id}")
    Call<Staff> modifyStaff(@Path("id") long id, @Body StaffDTO staffDTO);

     //Client

    @GET("clients")
    Call<List<Client>> getClients();

    @POST("clients")
    Call<Client> addClient(@Body ClientDTO clientDTO);

    @DELETE("client/{id}")
    Call<Void> deleteClient(@Path("id") long id);

    @PUT("client/{id}")
    Call<Client> modifyClient(@Path("id") long id, @Body ClientDTO clientDTO);

     //Activity

    @GET("activities")
    Call<List<Activitie>> getActivities();

    @POST("activities")
    Call<Activitie> addActivity(@Body ActivityDTO activityDTO);

    @DELETE("activity/{id}")
    Call<Void> deleteActivity(@Path("id") long id);

    @PUT("activity/{id}")
    Call<Activitie> modifyActivity(@Path("id") long id, @Body ActivityDTO activityDTO);

     //Activity_Client

    @GET("activitiesclients")
    Call<List<Activity_Client>> getActivities_Clients();

    @POST("activitiesclients")
    Call<Activity_Client> addActivity_Client(@Body Activity_ClientDTO activity_clientDTO);

    @DELETE("activityclient/{id}")
    Call<Void> deleteActivity_Client(@Path("id") long id);

    @PUT("activityclient/{id}")
    Call<Activity_Client> modifyActivity_Client(@Path("id") long id, @Body Activity_ClientDTO activity_clientDTO);




}
