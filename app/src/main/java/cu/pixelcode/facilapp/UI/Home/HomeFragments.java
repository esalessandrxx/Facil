
package cu.pixelcode.facilapp.Main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.transition.MaterialFadeThrough;
import cu.pixelcode.facilapp.Adaptars.GridItems;
import cu.pixelcode.facilapp.Adaptars.ItemClickListener;
import cu.pixelcode.facilapp.Adaptars.ListItems;
import cu.pixelcode.facilapp.Adaptars.ListViewAdapter;
import cu.pixelcode.facilapp.Adaptars.GridViewAdapter;
import cu.pixelcode.facilapp.Datos.PaquetesFragment;
import cu.pixelcode.facilapp.Llamadas.LlamadaFragment;
import cu.pixelcode.facilapp.MainActivity;
import cu.pixelcode.facilapp.Planes.PlanesFragment;
import cu.pixelcode.facilapp.Saldos.SaldoFragment;
import cu.pixelcode.facilapp.ui.Servicios.ServiciosFragment;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import cu.pixelcode.facilapp.R;
import android.view.View;
import androidx.fragment.app.Fragment;

public class HomeFragments extends Fragment {

	private RecyclerView recyclerView;
	private ListViewAdapter adapter;
	private ArrayList<ListItems> items = new ArrayList<>();
	Activity activity;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		if (context instanceof Activity) {
			activity = (Activity) context;
		}
	}

	public HomeFragments() {
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		((CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing)).setTitle("Fácil");
		items.clear();
		for (ListItems i : items) {
			ArrayList<ListItems> itemss = new ArrayList<>();
		}
		recyclerView = view.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
		adapter = new ListViewAdapter(getActivity(), items);
		recyclerView.setAdapter(adapter);
		items.add(new ListItems(R.drawable.ic_main_list_item1, "Saldo", "Recargue o envie saldo a sus contactos"));
		items.add(new ListItems(R.drawable.ic_main_list_item2, "Llamadas", "Llame con número privado o asterisco 99"));
		items.add(new ListItems(R.drawable.ic_main_list_item3, "Paquetes", "Gestione sus paquetes de datos"));
		items.add(new ListItems(R.drawable.ic_main_list_item4, "Planes", "Gestione sus planes de voz, sms o amigo"));
		items.add(new ListItems(R.drawable.ic_main_list_item5, "Servicios", "Servicios útiles para el usuario"));
		items.add(new ListItems(R.drawable.ic_main_list_item6, "WiFi", "Conéctese a las wifi públicas"));
		recyclerView.addOnItemTouchListener(
				new ItemClickListener(getActivity(), recyclerView, new ItemClickListener.ClickListener() {
					@Override
					public void onClick(View view, int position) {
						switch (position) {
						case 0:
							SaldoFragment saldo = new SaldoFragment();
							getActivity().getSupportFragmentManager().beginTransaction()
									.replace(R.id.frame_layout, saldo).addToBackStack("saldo").commit();
							break;
						case 1:
							LlamadaFragment llamada = new LlamadaFragment();
							getActivity().getSupportFragmentManager().beginTransaction()
									.replace(R.id.frame_layout, llamada).addToBackStack("llamada").commit();
							break;
						case 2:
							((MainActivity) activity).paqueteClick();
							break;
						case 3:
							PlanesFragment planes = new PlanesFragment();
							getActivity().getSupportFragmentManager().beginTransaction()
									.replace(R.id.frame_layout, planes).addToBackStack("planes").commit();
							break;
						case 4:
							ServiciosFragment servicios = new ServiciosFragment();
							getActivity().getSupportFragmentManager().beginTransaction()
									.replace(R.id.frame_layout, servicios).addToBackStack("serv").commit();
							break;
						case 5:
							((MainActivity) activity).loginClick();
							break;
						}
					}

					@Override
					public void onLongClick(View view, int position) {
					}
				}));
		return view;
	}
}
