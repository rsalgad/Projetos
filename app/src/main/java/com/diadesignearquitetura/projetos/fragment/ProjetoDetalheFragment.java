package com.diadesignearquitetura.projetos.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.provider.MediaStore;
import android.text.Editable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.activity.ProjetoActivity;
import com.diadesignearquitetura.projetos.activity.ui.main.PageViewModel;
import com.diadesignearquitetura.projetos.model.Projeto;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjetoDetalheFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private View root;

    public TextInputEditText editTextTitulo,  editTextCliente,  editTextEndereco,  editTextMetragem,
            editTextData,  editTextEmpreendimento,  editTextComentario;
    public ImageView imageView;

    public String titulo, cliente, endereco, metragem, data, empreendimento, comentario;
    public Spinner spinner;
    private Projeto proj = new Projeto();

    private PageViewModel pageViewModel;

    public static ProjetoDetalheFragment newInstance(int index) {
        ProjetoDetalheFragment fragment = new ProjetoDetalheFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        ProjetoActivity.detalheFragment = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_projeto_detalhe, container, false);

        editTextTitulo = root.findViewById(R.id.editTextTitulo);
        editTextCliente = root.findViewById(R.id.editTextCliente);
        editTextEndereco = root.findViewById(R.id.editTextEndereco);
        editTextMetragem = root.findViewById(R.id.editTextMetragem);
        editTextData = root.findViewById(R.id.editTextData);
        editTextData.setFocusableInTouchMode(false);
        editTextEmpreendimento = root.findViewById(R.id.editTextEmpreendimento);
        editTextComentario = root.findViewById(R.id.editTextComentario);
        imageView = root.findViewById(R.id.imageView);
        spinner = root.findViewById(R.id.spinnerBase);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        editTextData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(root.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        if (ProjetoActivity.proj != null){
            PopularDadosDeProjetoExistente();
        }

        return root;
    }

    public void PopularDadosDeProjetoExistente(){
        Projeto p = ProjetoActivity.proj;
        editTextTitulo.setText(p.getTitulo());
        editTextCliente.setText(p.getCliente());
        editTextEndereco.setText(p.getEndereco());
        editTextMetragem.setText(Float.toString(p.getMetragem()));
        updateLabel(p.getData());
        editTextEmpreendimento.setText(p.getEmpreendimento());
        editTextComentario.setText(p.getComentario());
        //SharedPreferences preferences = this.getActivity().getSharedPreferences(MainActivity.ARQUIVO_PREFERENCIA, 0);
        //int projIndex = 0;
        //for (int i = 0; i < MainActivity.listaProjetos.size(); i++){
            //if (MainActivity.listaProjetos.get(i).getTitulo() == p.getTitulo()){
                //projIndex = i;
            //}
        //}
        //String imagemName = "imagemProjeto"+Integer.toString(projIndex);
        //if (preferences.contains(imagemName)){
            //String imagem = preferences.getString(imagemName, "");
            imageView.setImageBitmap(decodeToBase64(p.getImagem()));
        //}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri localImagem = data.getData();

            try {

                Bitmap imagem = MediaStore.Images.Media.getBitmap(root.getContext().getContentResolver(), localImagem);
                imageView.setImageBitmap(imagem);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //SharedPreferences preferences = this.getActivity().getSharedPreferences(MainActivity.ARQUIVO_PREFERENCIA, 0);
                //SharedPreferences.Editor editor = preferences.edit();
                //String nameImage = "imagemProjeto" + Integer.toString(MainActivity.listaProjetos.size());
                //editor.putString(nameImage, encodeToBase64(imagem));
                //editor.commit();
                String nameImage = encodeToBase64(imagem);
                proj.setImagem(nameImage);

            } catch (Exception e){

            }

        }
    }

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(myCalendar.getTime());
        }

    };

    private void updateLabel(Date date) {
        String myFormat = "dd/mm/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMANY);

        editTextData.setText(sdf.format(date));
    }

    public Projeto PegarDadosDaPagina(){
        titulo = editTextTitulo.getText().toString();
        cliente = editTextCliente.getText().toString();
        endereco = editTextEndereco.getText().toString();
        metragem = editTextMetragem.getText().toString();
        data = editTextData.getText().toString();
        empreendimento = editTextEmpreendimento.getText().toString();
        comentario = editTextComentario.getText().toString();
        proj.setTitulo(titulo);
        proj.setCliente(cliente);
        proj.setEndereco(endereco);
        proj.setMetragem(Float.parseFloat(metragem));
        proj.setData(myCalendar.getTime());
        proj.setEmpreendimento(empreendimento);
        proj.setComentario(comentario);

        return proj;
    }

    public static String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        return imageEncoded;
    }

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
