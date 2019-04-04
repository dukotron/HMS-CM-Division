package com.example.homie.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.homie.network.DTO.UserLoginDTO;
import com.example.homie.repository.UserRepository;
import com.example.homie.viewModel.util.InputDataValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isValidEmail;
    private MutableLiveData<Boolean> isValidPassword;

    private MutableLiveData<Boolean> isLoggedIn;

    private UserRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        //userRepository = UserRepository.getInstance();

        isValidEmail = new MutableLiveData<>();
        isValidPassword = new MutableLiveData<>();
        isLoggedIn = new MutableLiveData<>();

        isValidEmail.setValue(true);
        isValidPassword.setValue(true);
        isLoggedIn.setValue(false);
    }

    public void loginUser(String email, String password) {
        //if (checkEnteredData(email, password)) {
//            userRepository.loginAccount(email, password);
//            isLoggedIn.setValue(true);
            new LoginRequest(getApplication()).start(email, password);
        }


    boolean checkEnteredData(String email, String password) {
        boolean valid = true;

        if (!InputDataValidator.isEmailValid(email)) {
            isValidEmail.setValue(false);
            valid = false;
        }

        if (!InputDataValidator.isPasswordValid(password)) {
            isValidPassword.setValue(false);
            valid = false;
        }
        return valid;
    }

    public MutableLiveData<Boolean> getIsValidEmail() {
        return isValidEmail;
    }

    public MutableLiveData<Boolean> getIsValidPassword() {
        return isValidPassword;
    }

    public MutableLiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    private class LoginRequest implements Callback<UserLoginDTO> {

        private Context context;

        private LoginRequest(Context context) {
            this.context = context;
        }

        public void start(String email, String password) {
            Log.d("LoginRequestTask", "Login request started");

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();

            APIConnection client = retrofit.create(APIConnection.class);
            Call<UserLoginDTO> call = client.loginAccount(email, password);
            call.enqueue(this);

        }

        @Override
        public void onResponse(Call<UserLoginDTO> call, Response<UserLoginDTO> response) {

            Log.d("LoginRequestTask", "Login Response");
//            Intent intent = new Intent(context, MenuActivity.class);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //just for now
////            context.startActivity(intent);
            isLoggedIn.setValue(true);
        }

        @Override
        public void onFailure(Call<UserLoginDTO> call, Throwable t) {

        }
    }
}
