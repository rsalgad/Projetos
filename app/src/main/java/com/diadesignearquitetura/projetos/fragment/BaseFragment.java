package com.diadesignearquitetura.projetos.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diadesignearquitetura.projetos.R;
import com.diadesignearquitetura.projetos.activity.MainActivity;
import com.diadesignearquitetura.projetos.fragment.CategoriaFragment;
import com.diadesignearquitetura.projetos.model.Categoria;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    private Button botaoBaseBaixa;
    private Button botaoBaseMedia;
    private Button botaoBaseMediaAlta;
    private Button botaoBaseAlta;
    private TextView textViewBaseBaixa;
    private TextView textViewBaseMedia;
    private TextView textViewBaseMediaAlta;
    private TextView textViewBaseAlta;
    private View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set current fragment
        MainActivity.baseFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //set current fragment
        MainActivity.currentFragment = this;

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_base, container, false);

        //get references to ui elements
        botaoBaseBaixa = root.findViewById(R.id.botaoBaseBaixa);
        botaoBaseMedia = root.findViewById(R.id.botaoBaseMedia);
        botaoBaseMediaAlta = root.findViewById(R.id.botaoBaseMediaAlta);
        botaoBaseAlta = root.findViewById(R.id.botaoBaseAlta);
        textViewBaseBaixa = root.findViewById(R.id.textViewBaseBaixa);
        textViewBaseMedia= root.findViewById(R.id.textViewBaseMedia);
        textViewBaseMediaAlta= root.findViewById(R.id.textViewBaseMediaAlta);
        textViewBaseAlta= root.findViewById(R.id.textViewBaseAlta);

        //set click events
        botaoBaseBaixa.setOnClickListener(new ClasseParaCliqueDeBotao(0));
        botaoBaseMedia.setOnClickListener(new ClasseParaCliqueDeBotao(1));
        botaoBaseMediaAlta.setOnClickListener(new ClasseParaCliqueDeBotao(2));
        botaoBaseAlta.setOnClickListener(new ClasseParaCliqueDeBotao(3));

        //set textView click events
        textViewBaseBaixa.setOnClickListener(new ClasseParaCliqueDeBotao(0));
        textViewBaseMedia.setOnClickListener(new ClasseParaCliqueDeBotao(1));
        textViewBaseMediaAlta.setOnClickListener(new ClasseParaCliqueDeBotao(2));
        textViewBaseAlta.setOnClickListener(new ClasseParaCliqueDeBotao(3));

        //populate textviews with values in listaBases
        textViewBaseBaixa.setText(Integer.toString(MainActivity.listaBases[0]));
        textViewBaseMedia.setText(Integer.toString(MainActivity.listaBases[1]));
        textViewBaseMediaAlta.setText(Integer.toString(MainActivity.listaBases[2]));
        textViewBaseAlta.setText(Integer.toString(MainActivity.listaBases[3]));

        return root;
    }

    class ClasseParaCliqueDeBotao implements View.OnClickListener {

        int i;

        public ClasseParaCliqueDeBotao(int i) {
            this.i = i;
        }

        @Override
        public void onClick(View v) {
            CliqueDeBotao(i, v);
        }
    }

    private void CliqueDeBotao(final int index, View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

        LinearLayout layout = new LinearLayout(view.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        // adicionar edittext para selecionar valor
        final EditText valorBox = new EditText(view.getContext());
        valorBox.setInputType(InputType.TYPE_CLASS_NUMBER);

        switch (index){
            case 0:
                alertDialog.setTitle(R.string.config_base_baixa);
                if (textViewBaseBaixa.getText() == "0") {
                    valorBox.setHint(R.string.generico_valor);
                } else {
                    valorBox.setText(textViewBaseBaixa.getText());
                }
                break;
            case 1:
                if (textViewBaseMedia.getText() == "0") {
                    alertDialog.setTitle(R.string.config_base_media);
                } else {
                    valorBox.setText(textViewBaseMedia.getText());
                }
                break;
            case 2:
                if (textViewBaseMediaAlta.getText() == "0") {
                    alertDialog.setTitle(R.string.config_base_media_alta);
                } else {
                    valorBox.setText(textViewBaseMediaAlta.getText());
                }
                break;
            default:
                if (textViewBaseAlta.getText() == "0") {
                    alertDialog.setTitle(R.string.config_base_alta);
                } else {
                    valorBox.setText(textViewBaseAlta.getText());
                }
                break;
        }

        layout.addView(valorBox); // Notice this is an add method
        alertDialog.setView(layout);

        alertDialog.setPositiveButton(R.string.generico_criar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int valor = Integer.parseInt(valorBox.getText().toString());
                MainActivity.listaBases[index] = valor;
                switch (index){
                    case 0:
                        textViewBaseBaixa.setText(Integer.toString(valor));
                        break;
                    case 1:
                        textViewBaseMedia.setText(Integer.toString(valor));
                        break;
                    case 2:
                        textViewBaseMediaAlta.setText(Integer.toString(valor));
                        break;
                    default:
                        textViewBaseAlta.setText(Integer.toString(valor));
                        break;
                }
            }
        });

        alertDialog.setNegativeButton(R.string.generico_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.create();
        alertDialog.show();
    }

}
