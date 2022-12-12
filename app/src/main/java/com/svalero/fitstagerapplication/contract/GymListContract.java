package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Gym;

import java.util.List;

public interface GymListContract {
    interface Model {

        interface OnLoadGymsListener {
            void onLoadGymsSuccess(List<Gym> gyms);

            void onLoadGymsError(String message);
        }

        interface OnDeleteGymListener{
            void onDeleteGymSuccess(String message);

            void onDeleteGymError(String message);
        }

        void startDb(Context context);

        void loadAllGyms(OnLoadGymsListener listener);

        void loadGymsByName(OnLoadGymsListener listener, String query);

        void loadGymsByCity(OnLoadGymsListener listener, String query);

        void loadGymsByStreet(OnLoadGymsListener listener, String query);

        void delete(OnDeleteGymListener listener, Gym gym);
    }

    interface View {
        void listGyms(List<Gym> gyms);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllGyms();

        void loadGymsByName(String query);

        void loadGymsByCity(String query);

        void loadGymsByStreet(String query);

        void deleteGym(Gym gym);
    }
}
