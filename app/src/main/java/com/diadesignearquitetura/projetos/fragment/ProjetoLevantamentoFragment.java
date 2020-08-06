package com.diadesignearquitetura.projetos.fragment;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.activity.ProjetoActivity;
import com.diadesignearquitetura.projetos.activity.ui.main.PageViewModel;
import com.diadesignearquitetura.projetos.model.Acao;
import com.diadesignearquitetura.projetos.model.Categoria;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjetoLevantamentoFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private LinearLayout linearLayout;
    private View root;
    private TextView valor;
    private LinearLayout valorLinearLayout;

    private List<CheckBox> listaAcoes = new ArrayList<CheckBox>();

    public static ProjetoLevantamentoFragment newInstance(int index) {
        ProjetoLevantamentoFragment fragment = new ProjetoLevantamentoFragment();
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
        ProjetoActivity.levantamentoFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_projeto_levantamento, container, false);
        linearLayout = root.findViewById(R.id.linearLayout);
        SetUpFragment();
        return root;
    }

    private void SetUpFragment(){
        DisplayMetrics dm = root.getResources().getDisplayMetrics();
        int margin = 8;
        int pixels = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin, dm));

        //lp.setMargins(pixels, pixels, pixels, pixels);

        for (int i = 0; i < MainActivity.listaCategorias.size(); i++){
            TextView cat = new TextView(root.getContext());
            TextViewCompat.setTextAppearance(cat, R.style.Levantamento_Categoria);
            Categoria categoria = MainActivity.listaCategorias.get(i);
            cat.setText(categoria.getTitulo().toUpperCase());
            linearLayout.addView(cat);
            for (int j = 0; j < MainActivity.listaAcoes.size(); j++){
                Acao acao = MainActivity.listaAcoes.get(j);
                if (acao.getCategoria().getTitulo() == categoria.getTitulo()){
                    CheckBox titulo = new CheckBox(root.getContext());
                    titulo.setText(acao.getTitulo().toUpperCase());
                    TextViewCompat.setTextAppearance(titulo, R.style.Levantamento_Acao);
                    titulo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            UpdateValor();
                        }
                    });
                    linearLayout.addView(titulo);
                    listaAcoes.add(titulo);
                }
            }
        }

        SetUpValorText();
    }

    private float CalculaMedia(){
        float sum = 0;
        int count = 0;
        for (int i = 0; i < listaAcoes.size(); i++){
            if (listaAcoes.get(i).isChecked()){
                String nomeAcao = listaAcoes.get(i).getText().toString();
                for (int j = 0; j < MainActivity.listaAcoes.size(); j++){
                    Acao acao = MainActivity.listaAcoes.get(j);
                    String acaoNome = acao.getTitulo().toUpperCase();
                    if (nomeAcao.equals(acaoNome)){
                        sum += acao.getValor();
                        count++;
                    }
                }
            }
        }
        if (count != 0) {
            return sum / count;
        } else {
            return 0;
        }
    }

    private void UpdateValor(){
        float valorProjeto = 0;
        if (ProjetoActivity.detalheFragment.editTextMetragem != null && ProjetoActivity.detalheFragment.editTextMetragem.getText().toString() != "") {
            try {
                valorProjeto = (CalculaMedia() * Float.parseFloat(ProjetoActivity.detalheFragment.editTextMetragem.getText().toString()) * MainActivity.listaBases[ProjetoActivity.detalheFragment.spinner.getSelectedItemPosition()]);
            } catch (Exception e){

            }
        }
        //DecimalFormat df = new DecimalFormat("#.##");
        valor.setText(" R$ " + Float.toString(valorProjeto));
        valorLinearLayout.setVisibility(View.VISIBLE);
    }

    private void SetUpValorText(){
        valorLinearLayout = new LinearLayout(root.getContext());
        /*
        Resources r = getResources();
        float pixelTopMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, r.getDisplayMetrics());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, Math.round(pixelTopMargin), 0, 0);
        */
        TextView total = new TextView(root.getContext());
        TextViewCompat.setTextAppearance(total, R.style.Levantamento_Categoria);
        total.setText(R.string.proj_levantamento_valor);
        valorLinearLayout.addView(total);
        valor = new TextView(root.getContext());
        TextViewCompat.setTextAppearance(valor, R.style.Levantamento_Valor);
        valorLinearLayout.addView(valor);
        linearLayout.addView(valorLinearLayout);
        valorLinearLayout.setVisibility(View.INVISIBLE);
    }
}
