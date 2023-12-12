package com.example.pr_final_303

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.pr_final_303.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nombre: EditText = findViewById(R.id.txtFullName)
        val correo: EditText = findViewById(R.id.txtEmailRegister)
        val pais: EditText = findViewById(R.id.txtCountry)
        val contraseña: EditText = findViewById(R.id.txtPasswordRegister)
        val btnRegister: Button = findViewById(R.id.btnSaveRegister)
        val btnR: Button = findViewById(R.id.btnR)

        btnR.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("users")


        btnRegister.setOnClickListener{

            val correo = correo.text.toString()
            val clave = contraseña.text.toString()
            val nombreCompleto = nombre.text.toString()
            val pais = pais.text.toString()

            auth.createUserWithEmailAndPassword(correo,clave)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        //Se registró en Firebase Auth y deberá registrarse en Firestore
                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid

                        val userModel = User(correo, clave, nombreCompleto, pais,uid.toString())
                        collectionRef.add(userModel)
                            .addOnCompleteListener{

                            }.addOnFailureListener{error ->
                                Snackbar
                                    .make(
                                        findViewById(android.R.id.content)
                                        ,"Ocurrió un error al registrar el modelo"
                                        , Snackbar.LENGTH_LONG
                                    ).show()
                            }
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Registro exitoso del usuario"
                                , Snackbar.LENGTH_LONG
                            ).show()


                    }else{
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Ocurrió un error al registrarse"
                                , Snackbar.LENGTH_LONG
                            ).show()
                    }


                }
        }
    }
}