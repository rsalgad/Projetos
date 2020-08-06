package com.diadesignearquitetura.projetos.activity;

import android.os.Bundle;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.fragment.FirstFragment;
import com.diadesignearquitetura.projetos.fragment.ProjetoDetalheFragment;
import com.diadesignearquitetura.projetos.fragment.ProjetoLevantamentoFragment;
import com.diadesignearquitetura.projetos.model.Projeto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.diadesignearquitetura.projetos.activity.ui.main.SectionsPagerAdapter;

public class ProjetoActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    public static ProjetoDetalheFragment detalheFragment;
    public static ProjetoLevantamentoFragment levantamentoFragment;
    public static Projeto proj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Bundle dados = getIntent().getExtras();
        try {
            proj = (Projeto) dados.getSerializable("projeto");
        } catch (Exception e){

        }

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Projeto p = detalheFragment.PegarDadosDaPagina();
                MainActivity.listaProjetos.add(p);
                FirstFragment frag = (FirstFragment)MainActivity.firstFragment;
                frag.UpdateRecyclerView();
                finish();
            }
        });
    }
}