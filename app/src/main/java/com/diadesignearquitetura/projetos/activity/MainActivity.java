package com.diadesignearquitetura.projetos.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.fragment.AcaoFragment;
import com.diadesignearquitetura.projetos.fragment.CategoriaFragment;
import com.diadesignearquitetura.projetos.fragment.FirstFragment;
import com.diadesignearquitetura.projetos.model.Acao;
import com.diadesignearquitetura.projetos.model.Categoria;
import com.diadesignearquitetura.projetos.model.Projeto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Projeto> listaProjetos = new ArrayList<Projeto>();
    public static List<Categoria> listaCategorias = new ArrayList<Categoria>();
    public static List<Acao> listaAcoes = new ArrayList<Acao>();
    public static int[] listaBases;

    public static Fragment currentFragment;
    private Menu topMenu;
    private MenuItem settingsItem;
    public static Toolbar toolbar;
    public static FloatingActionButton fab;
    public static Fragment firstFragment;
    public static Fragment secondFragment;
    public static Fragment categoriaFragment;
    public static Fragment acaoFragment;
    public static Fragment baseFragment;

    public static final String ARQUIVO_PREFERENCIA = "AruivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicializa as bases para 0
        listaBases = new int[] {0,0,0,0};

        //acha o floating action button
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new ClasseParaCliqueDeFAB());
        //para propósitos de teste apenas
        /*
        Projeto p1 = new Projeto("Projeto 1", new Date(), "Reforma da sala do João Carlos", R.drawable.reforma);
        Projeto p2 = new Projeto("Projeto 2", new Date(), "Apartamento da Joana", R.drawable.apartamento );
        Projeto p3 = new Projeto("Projeto 3", new Date(), "Decoração da loja TendiTudo", R.drawable.loja);
        listaProjetos.add(p1);
        listaProjetos.add(p2);
        listaProjetos.add(p3);
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        topMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        CliqueDeMenuItem(item);

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    private void CliqueDeMenuItem(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            NavHostFragment.findNavController(firstFragment)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
            settingsItem = item;
            settingsItem.setVisible(false);
            //para adicionar botão de voltar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setTitle(R.string.toolbar_titulo_configuracoes);
            fab.hide();
        } else if (id == android.R.id.home) {
            if (currentFragment == secondFragment) {
                settingsItem.setVisible(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                toolbar.setTitle(R.string.toolbar_titulo_projetos);
                fab.show();
            } else if (currentFragment == categoriaFragment || currentFragment == acaoFragment || currentFragment == baseFragment){
                toolbar.setTitle(R.string.toolbar_titulo_configuracoes);
                fab.hide();
            }
            super.onBackPressed();
        }
    }

    class ClasseParaCliqueDeFAB implements View.OnClickListener {

        public ClasseParaCliqueDeFAB() {
        }

        @Override
        public void onClick(View v) {
            CliqueDeFAB(v);
        }
    }

    private void CliqueDeFAB(View view){

        if(currentFragment == firstFragment){
            Intent intent = new Intent(getApplicationContext(), ProjetoActivity.class);
            startActivity(intent);
        }
        else if (currentFragment == categoriaFragment){

            PopUpCriarCategoria(view, null);

        } else if (currentFragment == acaoFragment) {

            PopUpCriarAcao(view, null);

        }

    }

    public static void PopUpCriarCategoria(View view, Categoria cat){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        alertDialog.setTitle("Criar Categoria");

        LinearLayout layout = new LinearLayout(view.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        // Add a TextView here for the "Title" label, as noted in the comments
        final EditText titleBox = new EditText(view.getContext());
        if (cat == null) {
            titleBox.setHint("Nome da categoria");
        } else {
            titleBox.setText(cat.getTitulo());
        }
        layout.addView(titleBox); // Notice this is an add method

        alertDialog.setView(layout);

        alertDialog.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Categoria cat = new Categoria(titleBox.getText().toString(), MainActivity.listaCategorias.size() + 1);
                MainActivity.listaCategorias.add(cat);
                CategoriaFragment frag = (CategoriaFragment)categoriaFragment;
                frag.UpdateRecyclerView();
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    public static void PopUpCriarAcao(View view, Acao acao){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
        alertDialog.setTitle("Criar Ação");

        LinearLayout layout = new LinearLayout(view.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        // Add a TextView here for the "Title" label, as noted in the comments
        final EditText titleBox = new EditText(view.getContext());
        final EditText valorBox = new EditText(view.getContext());
        valorBox.setInputType(InputType.TYPE_CLASS_NUMBER);
        final Spinner listaCategorias = new Spinner(view.getContext());

        List<String> catNames = new ArrayList<String>();
        int pos = 0;
        for (int i = 0; i < MainActivity.listaCategorias.size(); i++){
            catNames.add(MainActivity.listaCategorias.get(i).getTitulo());
            if (acao != null){
                if (acao.getCategoria().getTitulo() == MainActivity.listaCategorias.get(i).getTitulo()){
                    pos = i;
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,catNames);
        listaCategorias.setAdapter(adapter);

        if (acao == null) {
            titleBox.setHint("Nome da ação");
            valorBox.setHint("Valor da ação");
        } else {
            titleBox.setText(acao.getTitulo());
            valorBox.setText(Integer.toString(acao.getValor()));
            listaCategorias.setSelection(pos);
        }
        layout.addView(titleBox); // Notice this is an add method
        layout.addView(valorBox);
        layout.addView(listaCategorias);

        alertDialog.setView(layout);

        alertDialog.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Acao acao = new Acao(titleBox.getText().toString(), Integer.parseInt(valorBox.getText().toString()),MainActivity.listaCategorias.get(listaCategorias.getSelectedItemPosition()));
                MainActivity.listaAcoes.add(acao);
                AcaoFragment frag = (AcaoFragment)acaoFragment;
                frag.UpdateRecyclerView();
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.create();
        alertDialog.show();
    }

}
