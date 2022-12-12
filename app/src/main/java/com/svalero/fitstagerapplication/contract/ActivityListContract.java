package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Activitie;
import com.svalero.fitstagerapplication.domain.Client;

import java.util.List;

public interface ActivityListContract {



    interface Model {
        interface OnLoadActivitiesListener {
            void onLoadActivitiesSuccess(List<Activitie> activities);

            void onLoadActivitiesError(String message);
        }

        interface OnDeleteActivityListener{
            void onDeleteActivitySuccess(String message);

            void onDeleteActivityError(String message);
        }

        void startDb(Context context);

        void loadAllActivities(ActivityListContract.Model.OnLoadActivitiesListener listener);

        //void loadStaffsByBrand(OnLoadStaffsListener listener, String query);

        //void loadStaffsByModel(OnLoadStaffsListener listener, String query);

        //void loadStaffsByRam(OnLoadStaffsListener listener, String query);

        void delete(ActivityListContract.Model.OnDeleteActivityListener listener, Activitie activity);
    }

    interface View {
        void listActivities(List<Activitie> activityies);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllActivities();

        //void loadStaffsByBrand(String query);

        //void loadStaffsByModel(String query);

        //void loadStaffsByRam(String query);

        void deleteActivity(Activitie activity);
    }
}


