package com.diadesignearquitetura.projetos.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.activity.MainActivity;

public class SecondFragment extends Fragment {

    private Button botaoCategoria;
    private Button botaoAcao;
    private Button botaoBase;
    private View root;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.secondFragment = this;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_second, container, false);

        //get reference to ui elements
        botaoCategoria = root.findViewById(R.id.botaoCategoria);
        botaoAcao = root.findViewById(R.id.botaoAcao);
        botaoBase = root.findViewById(R.id.botaoBase);

        //set this as the current fragment
        MainActivity.currentFragment = this;

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConfiguraAcoesDeBotoes();
    }

    private void ConfiguraAcoesDeBotoes(){
        botaoCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_categoriaFragment);
                MainActivity.toolbar.setTitle(R.string.toolbar_titulo_categorias);
                MainActivity.fab.show();
            }
        });

        botaoAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_acaoFragment);
                MainActivity.toolbar.setTitle(R.string.toolbar_titulo_acoes);
                MainActivity.fab.show();
            }
        });

        botaoBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_baseFragment);
                MainActivity.toolbar.setTitle(R.string.toolbar_titulo_bases);
                MainActivity.fab.hide();
            }
        });
    }
}
