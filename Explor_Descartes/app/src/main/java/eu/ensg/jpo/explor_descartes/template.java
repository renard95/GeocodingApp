package eu.ensg.jpo.explor_descartes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.ensg.jpo.explor_descartes.Menu.Menu;

public class template extends AppCompatActivity {

    /**
     *  Classe gérant la mise en page de l'application
     */

    // Déclaration des variables
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private ImageButton imgLeft, imgRight;
    private GridView gv;
    private LinearLayout drawer_right;
    private ListView drawer_left;

    private String menulist[];
    private String menul;
    private Context con = this;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Menu menuc = new Menu(con);
    private int layoutResID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        llayout();
        setContentView(layoutResID);

        // Instanciation des variables de Layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.mytabs);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer_left = (ListView) findViewById(R.id.leftMenuList);
        drawer_right = (LinearLayout) findViewById(R.id.rightMenu);
        imgLeft = (ImageButton) findViewById(R.id.imgLeft);
        imgRight = (ImageButton) findViewById(R.id.imgRight);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Ouverture du menu gauche par click sur l'image du menu
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(drawer_left)) {
                    mDrawerLayout.closeDrawer(drawer_left);
                } else if (!mDrawerLayout.isDrawerOpen(drawer_left)) {
                    mDrawerLayout.openDrawer(drawer_left);
                }
                if (mDrawerLayout.isDrawerOpen(drawer_right)) {
                    mDrawerLayout.closeDrawer(drawer_right);
                }
            }
        });

        // Ouverture du menu droit par click sur l'image du menu
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(drawer_right)) {
                    mDrawerLayout.closeDrawer(drawer_right);
                } else if (!mDrawerLayout.isDrawerOpen(drawer_right)) {
                    mDrawerLayout.openDrawer(drawer_right);
                }
                if (mDrawerLayout.isDrawerOpen(drawer_left)) {
                    mDrawerLayout.closeDrawer(drawer_left);
                }
            }
        });

        // Appel de la méthode servant à récupérer les données
        try {
            getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }

    private void setViewPager(ViewPager viewPager) {

        /**
         * Méthode pour remplir le menu notifications et favoris (ajout de tabs)
         */

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment(new FavorisFragment(), "Favoris");
        //adapter.addFragment(new NotificationsFragment(), "Notifications");
        viewPager.setAdapter(adapter);
    }

    public void getData() throws ParseException {

        /**
         * Méthode pour stocker et récupérer les données
        */

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(ListeObjets.dateJPO);

        menul = "Train,Bus Oth,Bus Muni";
        menulist = menul.split(",");




        final ArrayAdapter<String> adapterLeft = new ArrayAdapter<>(this, R.layout.textcenter, R.id.textItem, menulist);
        drawer_left.setAdapter(adapterLeft);
        drawer_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Menu
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 1) {
                    menuc.openTrain();
                }
                if (pos == 2) {
                    menuc.openBusOth();
                }
                if (pos == 3) {
                    menuc.openBusMuni();
                }
            }
        });
        View headerView = getLayoutInflater().inflate(R.layout.listview_header, null);
        drawer_left.addHeaderView(headerView);
    }

    protected void contentTemp() {}
    protected void llayout(){}

    public void setLayout(int newLayout){
        this.layoutResID = newLayout;
    }
}