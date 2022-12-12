package com.svalero.fitstagerapplication.contract;

import android.content.Context;

import com.svalero.fitstagerapplication.domain.Staff;

import java.util.List;



    public interface StaffListContract {


        interface Model {
            interface OnLoadStaffsListener {
                void onLoadStaffsSuccess(List<Staff> staffs);

                void onLoadStaffsError(String message);
            }

            interface OnDeleteStaffListener{
                void onDeleteStaffSuccess(String message);

                void onDeleteStaffError(String message);
            }

            void startDb(Context context);

            void loadAllStaffs(OnLoadStaffsListener listener);

            //void loadStaffsByBrand(OnLoadStaffsListener listener, String query);

            //void loadStaffsByModel(OnLoadStaffsListener listener, String query);

            //void loadStaffsByRam(OnLoadStaffsListener listener, String query);

            void delete(OnDeleteStaffListener listener, Staff staff);
        }

        interface View {
            void listStaffs(List<Staff> staffs);

            void showMessage(String message);
        }

        interface Presenter {
            void loadAllStaffs();

            //void loadStaffsByBrand(String query);

            //void loadStaffsByModel(String query);

            //void loadStaffsByRam(String query);

            void deleteStaff(Staff staff);
        }
    }





