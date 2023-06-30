package com.example.apptrangsuc.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptrangsuc.R;
import com.example.apptrangsuc.activity.LoginActivity;
import com.example.apptrangsuc.sever.SERVER;
import com.example.apptrangsuc.activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {

    public static String email;
    public static SharedPreferences sharedPreferences;
    private EditText etName, etPhone, etAddress;
    private Button btnUpdate, btnChangePassword, btnLogout, btnSaveprofile;
    private RequestQueue requestQueue;
    private ImageView backButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_profile, container, false);
        requestQueue = Volley.newRequestQueue(requireContext());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // Initialize the views
        etName = view.findViewById(R.id.p_Name);
        etAddress = view.findViewById(R.id.p_address);
        etPhone = view.findViewById(R.id.p_phone);


        sharedPreferences = requireContext().getSharedPreferences("email", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");

        backButton = view.findViewById(R.id.imgback);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        btnLogout = view.findViewById(R.id.btnlogout);

        // Gọi phương thức fetchProfileInfo để lấy thông tin từ máy chủ
        fetchProfileInfo(email);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trở về MainActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("fragmentToLoad", "home");
                startActivity(intent);
                getActivity().finish();
            }
        });



        // Set click listener for the logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(requireContext(), LoginActivity.class);
                        startActivity(intent);
                        requireActivity().finishAffinity();
                        Toast.makeText(requireContext(), "Bạn đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });




        // Set click listener for the update button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(requireContext());
                View dialogView = inflater.inflate(R.layout.edit_profile, null);

                EditText etNewName = dialogView.findViewById(R.id.etNewName);
                EditText etNewAddress = dialogView.findViewById(R.id.etNewAddress);
                EditText etNewPhone = dialogView.findViewById(R.id.etNewPhone);

                etNewName.setText(etName.getText().toString());
                etNewAddress.setText(etAddress.getText().toString());
                etNewPhone.setText(etPhone.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Edit Profile");
                builder.setView(dialogView);
                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = etNewName.getText().toString();
                        String newAddress = etNewAddress.getText().toString();
                        String newPhone = etNewPhone.getText().toString();

                        if (TextUtils.isEmpty(newName) || TextUtils.isEmpty(newAddress) || TextUtils.isEmpty(newPhone)) {
                            Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            updateProfile(email,newName, newAddress, newPhone);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });





        // Set click listener for the change password button
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(requireContext());
                View changePasswordView = inflater.inflate(R.layout.change_password, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Change Password");
                builder.setView(changePasswordView);
                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etOldPassword = changePasswordView.findViewById(R.id.edt_old_password);
                        EditText etNewPassword = changePasswordView.findViewById(R.id.edt_new_password);
                        EditText etConfirmPassword = changePasswordView.findViewById(R.id.edt_conform_password);

                        // Retrieve the entered values
                        String oldPassword = etOldPassword.getText().toString().trim();
                        String newPassword = etNewPassword.getText().toString().trim();
                        String confirmPassword = etConfirmPassword.getText().toString().trim();

                        // Validate the input fields
                        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        } else if (!newPassword.equals(confirmPassword)) {
                            Toast.makeText(requireContext(), "New password and confirm password do not match",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            updatePassword(newPassword);
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });
    }

    void fetchProfileInfo(String email){
        String url = SERVER.profile+"?email='"+email+"'";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String hoten = jsonObject.getString("name");
                        String diachi = jsonObject.getString("address");
                        String sodienthoai = jsonObject.getString("phone");


                        etName.setText(hoten);
                        etAddress.setText(diachi);
                        etPhone.setText(sodienthoai);


                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }

    private void updateProfile(final String email, final String newName, final String newAddress, final String newPhone) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.udapte, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Success")) {
                    etName.setText(newName);
                    etAddress.setText(newAddress);
                    etPhone.setText(newPhone);
                    Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(requireContext(), "Error updating profile", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("hoten", newName);
                params.put("diachi", newAddress);
                params.put("sodienthoai", newPhone);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
    private void updatePassword(String newPassword){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.changepass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    Toast.makeText(getContext(), "Password Update Successful", Toast.LENGTH_SHORT).show();
                } else if (response.equals("Failure")) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", email);
                data.put("newPassword", newPassword);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}

