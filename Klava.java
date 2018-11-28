package kz.mazur.generator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Klava extends Fragment implements View.OnClickListener{
     Button k0,k1,k2,k3,k4,k5,k6,k7,k8,k9,pusk;
   ImageButton delete;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.klava,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        k0=(Button)getActivity().findViewById(R.id.k0);
        k1=(Button)getActivity().findViewById(R.id.k1);
        k2=(Button)getActivity().findViewById(R.id.k2);
        k3=(Button)getActivity().findViewById(R.id.k3);
        k4=(Button)getActivity().findViewById(R.id.k4);
        k5=(Button)getActivity().findViewById(R.id.k5);
        k6=(Button)getActivity().findViewById(R.id.k6);
        k7=(Button)getActivity().findViewById(R.id.k7);
        k8=(Button)getActivity().findViewById(R.id.k8);
        k9=(Button)getActivity().findViewById(R.id.k9);
        pusk=(Button)getActivity().findViewById(R.id.pusk);
        delete=(ImageButton)getActivity().findViewById(R.id.delete);
    }

    @Override
    public void onClick(View view) {
      switch (getId()){
          case R.id.k0:
              break;
          case R.id.k1:
              break;
          case R.id.k2:
              break;
          case R.id.k3:
              break;
          case R.id.k4:
              break;
          case R.id.k5:
              break;
          case R.id.k6:
              break;
          case R.id.k7:
              break;
          case R.id.k8:
              break;
          case R.id.k9:
              break;
          case R.id.pusk:
              break;
          case R.id.delete:
              break;
      }
    }
}
