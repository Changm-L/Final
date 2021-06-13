package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth; //파이어베이스 인증
    private DatabaseReference databaseReference; // 실시간 데이터베이스
    private EditText et_id, et_pw, et_name, et_add;
    private Button btn_register;
    private ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDBLogin");

        et_id=findViewById(R.id.edtext_id);
        et_pw=findViewById(R.id.edtext_pw);
        et_name=findViewById(R.id.edtext_name);
        et_add=findViewById(R.id.edtext_add);
        img_back = findViewById(R.id.back);
        btn_register = findViewById(R.id.btn_regi);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 처리
                String strId = et_id.getText().toString();
                String strPw = et_pw.getText().toString();
                String strName = et_name.getText().toString();
                String strAdd = et_add.getText().toString();

                //파이어베이스 어스 진행
                firebaseAuth.createUserWithEmailAndPassword(strId,strPw).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setId(firebaseUser.getEmail());
                            account.setPw(strPw);
                            account.setName(strName);
                            account.setAdd(strAdd);

                            //setValue : database에 insert 하는 행위
                            databaseReference.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(RegisterActivity.this,"회원가입에 성공했습니다.",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterActivity.this,"회원가입에 실패했습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}