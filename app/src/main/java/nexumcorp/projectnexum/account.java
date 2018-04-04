package nexumcorp.projectnexum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nexumcorp.projectnexum.accounts.LoginActivity;

/**
 * Created by SinghA02 on 03/03/2018.
 */

public class account extends Fragment {
    private static final String TAG = "Account";
    private FirebaseAuth.AuthStateListener mauthStateListener;


    //widgets(Button)
    private Button mSignOut;
    private Button addBusiness;
    private FragmentActivity myContext;
    private String username;
    private String email;
    private TextView nametf;
    private TextView emailtf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account, container, false);

        mSignOut = (Button) view.findViewById(R.id.add_business);

        TextView nametf= (TextView) view.findViewById(R.id.NameTf);
        TextView emailtf = (TextView) view.findViewById(R.id.emailTf);
        setupFirebaseListener();
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"Attempting to sign out User");
                FirebaseAuth.getInstance().signOut();
            }
        });

        nametf.setText(username);
        emailtf.setText(email);

        return view;

    }

    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener authStateListener= new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if(firebaseUser !=null){
                username=firebaseUser.getDisplayName();
                email=firebaseUser.getEmail();
            }
        }
    };


    private void setupFirebaseListener(){
        Log.d(TAG,"Setting up the auth state listener");
        mauthStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG,"signed_in"+user.getUid());
                }else{
                    Log.d(TAG,"onAuthstateChanged: signed_out");
                    Toast.makeText(getActivity(),"Signed out",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getActivity(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mauthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mauthStateListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mauthStateListener);
        }
    }
}
