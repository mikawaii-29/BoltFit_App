package com.example.boltfit_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    ImageView menuBtn;
    ImageButton upperbodyBtn, lowerbodyBtn, cardioBtn, stretchBtn,
            pushup_Btn, plank_Btn, armcircles_Btn, tricep_Btn;
    TextView workoutName, email, name, textView10;
    String username;
    DatabaseHelper db;
    LinearLayout scrollContent;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        scrollContent = findViewById(R.id.scroll_content);
        inflater = LayoutInflater.from(this);

        pushup_Btn = findViewById(R.id.pushup_btn);
        plank_Btn = findViewById(R.id.plank_btn);
        armcircles_Btn = findViewById(R.id.armCircles_btn);
        tricep_Btn = findViewById(R.id.triceps_btn);
        workoutName = findViewById(R.id.workout_name);
        upperbodyBtn = findViewById(R.id.upperbody_contentBtn);
        lowerbodyBtn = findViewById(R.id.lowerbody_contentBtn);
        cardioBtn = findViewById(R.id.cardio_contentBtn);
        stretchBtn = findViewById(R.id.stretch_contentBtn);
        menuBtn = findViewById(R.id.menu_button);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        initializeViews();
        setupNavigationDrawer();

        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");

        if (username != null) {
            textView10.setText("Hi " + username + "!");
            int userId = db.getUserIdByUsername(username);


        }

        displayUserDetails();
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        setupExerciseButtons();
        setupContentButtons();

    }

    private void initializeViews() {

        name = findViewById(R.id.nameText1);
        email = findViewById(R.id.emailText1);
        textView10 = findViewById(R.id.textView10);
    }

    private void setupNavigationDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.nameText1);
        email = headerView.findViewById(R.id.emailText1);
        menuBtn.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
    }


    private void displayUserDetails() {
        if (username != null && !username.isEmpty()) {
            String[] userDetails = db.getUserDetailsByUsername(username);

            if (userDetails != null && userDetails.length >= 2 && userDetails[0] != null && userDetails[1] != null) {
                name.setText(userDetails[0]);
                email.setText(userDetails[1]);
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No username provided", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupExerciseButtons() {
        pushup_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Pushup_Exercise.class));
            }
        });

        plank_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Plank_Exercise.class));
            }
        });

        armcircles_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, ArmCircles_Exercise.class));
            }
        });

        tricep_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, TricepDips_Exercise.class));
            }
        });
    }

    private void setupContentButtons() {
        upperbodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutName.setText("UPPER BODY WORKOUT");
                updateScrollViewContent(R.layout.layout_upperbody);
            }
        });

        lowerbodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutName.setText("LOWER BODY WORKOUT");
                updateScrollViewContent(R.layout.layout_lowerbody);
            }
        });

        cardioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutName.setText("CARDIO WORKOUT");
                updateScrollViewContent(R.layout.layout_cardio);
            }
        });

        stretchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutName.setText("STRETCHING WORKOUT");
                updateScrollViewContent(R.layout.layout_stretch);
            }
        });
    }

    private void updateScrollViewContent(@LayoutRes int layoutResId) {
        scrollContent.removeAllViews();
        View newContent = inflater.inflate(layoutResId, scrollContent, false);
        scrollContent.addView(newContent);

        if (layoutResId == R.layout.layout_upperbody) {
            ImageButton PushupBtn = newContent.findViewById(R.id.pushup_BTN);
            ImageButton PlankBtn = newContent.findViewById(R.id.plank_BTN);
            ImageButton ArmcirclesBtn = newContent.findViewById(R.id.armCircles_BTN);
            ImageButton TricepsBtn = newContent.findViewById(R.id.triceps_BTN);
            PushupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Pushup_Exercise.class));
                }
            });
            PlankBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Plank_Exercise.class));
                }
            });
            ArmcirclesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, ArmCircles_Exercise.class));
                }
            });
            TricepsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, TricepDips_Exercise.class));
                }
            });

        }

        if (layoutResId == R.layout.layout_lowerbody) {
            ImageButton squatsBtn = newContent.findViewById(R.id.squats_btn);
            ImageButton lungesBtn = newContent.findViewById(R.id.lunges_btn);
            ImageButton gluteBtn = newContent.findViewById(R.id.glute_btn);
            ImageButton donkeyBtn = newContent.findViewById(R.id.donkey_btn);
            squatsBtn.setOnClickListener(v -> startActivity(new Intent(Dashboard.this, Squats_Exercise.class)));
            lungesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Lunges_Exercise.class));
                }
            });
            gluteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Glute_Exercise.class));
                }
            });
            donkeyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Donkey_Exercise.class));
                }
            });

        }

        if (layoutResId == R.layout.layout_cardio) {
            ImageButton jumpingBtn = newContent.findViewById(R.id.jumping_btn);
            ImageButton highkneesBtn = newContent.findViewById(R.id.highkness_btn);
            ImageButton frogBtn = newContent.findViewById(R.id.frog_btn);
            ImageButton buttBtn = newContent.findViewById(R.id.butt_btn);
            jumpingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Jumping_Exercise.class));
                }
            });
            highkneesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, HighKnees_Exercise.class));
                }
            });
            frogBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, FrogJumps_Exercise.class));
                }
            });
            buttBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, ButtKickers_Exercise.class));
                }
            });

        }

        if (layoutResId == R.layout.layout_stretch) {
            ImageButton quadBtn = newContent.findViewById(R.id.quad_btn);
            ImageButton calfBtn = newContent.findViewById(R.id.calf_btn);
            ImageButton shoulderBtn = newContent.findViewById(R.id.shoulder_btn);
            ImageButton downwardBtn = newContent.findViewById(R.id.downward_btn);
            quadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, QuadStretch_Exercise.class));
                }
            });
            calfBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Calf_Exercise.class));
                }
            });
            shoulderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, Shoulder_Exercise.class));
                }
            });
            downwardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Dashboard.this, DownwardDog_Exercise.class));
                }
            });
        }
    }
}