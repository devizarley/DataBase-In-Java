package br.com.flap.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ImageView imagePhoto;
    private Button buttonUpload;

    /*
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Fazendo upload de uma foto*/

        imagePhoto = findViewById(R.id.imagePhoto);
        buttonUpload = findViewById(R.id.buttonUpload);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Configura para imagem ser salva em memoria
                imagePhoto.setDrawingCacheEnabled(true);
                imagePhoto.buildDrawingCache();

                //Recupera bitmap da imagem (image a ser carregada)
                Bitmap bitmap = imagePhoto.getDrawingCache();

                //Comprimo bitmap para um formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75,baos );

                //Converte o baos para pixel brutos em uma matriz de bytes
                //(dados da imagem)
                byte[] dadosImagem = baos.toByteArray();

                //Define nós para o storage
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");

                /*Baixar uma imagem pela referencia

                StorageReference imagemRef = imagens.child("celular.jpeg");

                Glide.with(MainActivity.this)
                        .load(imagemRef)
                        .into(imagePhoto);
                */
                /*Apagar uma imagem pela referencia
                StorageReference imagemRef = imagens.child("celular.jpeg");

                imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Erro ao deletar a imagem", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Sucesso ao deletar", Toast.LENGTH_SHORT).show();
                    }
                });*/

                /*Nome da imagem
                String nomeArquivo = UUID.randomUUID().toString();
                StorageReference imagemRef = imagens.child("celular.jpeg");


                //Retorna objeto que irá controlar o upload
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Upload da imagem falhou: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(MainActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                */
            }
        });


        /*Recuperar dados com filtros*/

        //DatabaseReference users = reference.child( "usuarios" );
        //DatabaseReference userSearch = users.child("-NLSlH4ag8H0fjSp3XrM");

        //Query UserSearch = users.orderByChild("nome").equalTo("Izarley");
        //Query UserSearch = users.orderByKey().limitToFirst(3);
        //Query UserSearch = users.orderByKey().limitToLast(2);

        /*Maior ou igual (>=)*/
        //Query UserSearch = users.orderByChild("idade").startAt(18);

        /*Maior ou igual (<=)*/
        //Query UserSearch = users.orderByChild("idade").endAt(18);

        /*Entre dois valores*/
        //Query UserSearch = users.orderByChild("idade").startAt(18).endAt(40);

        /*Filtrar palavras*/
        //Query UserSearch = users.orderByChild("nome").startAt("J").endAt("J" + "\uf8ff" );

        /*
        UserSearch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Usuario dataUser = snapshot.getValue(Usuario.class);
                //Log.i("Data user:", " nome: " + dataUser.getNome());
                Log.i("Data user:", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
         });*/


        /*Deslogar usuario
        auth.signOut();
        */
        /*Logar o usuario
        auth.signInWithEmailAndPassword("izarley.oliveira2@hotmail.com", "izarley12345")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("signIn", "Sucesso ao logar");
                        }else{
                            Log.i("signIn", "Erro ao logar");
                        }
                    }
                });
        */

        /*Verifica usuario logado
        if (auth.getCurrentUser() != null){
            Log.i("CurrentUser", "Usuario logado");
        }else {
            Log.i("CurrentUser", "Usuario não logado");
        }
        */

        /*Cadastro de usuario
        auth.createUserWithEmailAndPassword("izarley.oliveira2@hotmail.com", "izarley12345")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("CreatedUser", "Sucesso ao cadastrar usuario");
                        }else{
                            Log.i("CreatedUser", "Erro ao cadastrar usuario");
                        }
                    }
                });
        */
        /*Cria uma tabela e as referencias

        DatabaseReference users = reference.child( "usuarios" ).child("001");
        DatabaseReference products = reference.child( "produtos" );

        Usuario user = new Usuario();
        Produtos product = new Produtos();

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("Firebase", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        product.setDescricao("IOS");
        product.setMarca("Iphone 7");
        product.setPreco(3000.00);

        user.setNome("Maria");
        user.setSobrenome("Rodrigues");
        user.setIdade(30);

        products.child("001").setValue(product);
        users.child("002").setValue(user);
        */

    }
}