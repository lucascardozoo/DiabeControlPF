package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import frgp.utn.edu.ar.appmobile.fragmentAsistente;
import frgp.utn.edu.ar.appmobile.fragmentComida;
import frgp.utn.edu.ar.appmobile.fragmentGlucemia;
import frgp.utn.edu.ar.appmobile.fragmentHistorial;
public class TabAdapterViewPager extends FragmentStateAdapter {
    public TabAdapterViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new fragmentGlucemia();
            case 1:
                return new fragmentComida();
            case 2:
                return new fragmentHistorial();
            case 3:
                return new fragmentAsistente();
        }
        return null;
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
