# Beispiele

Hier finden Sie die im Tutorial entwickelten Beispiele. Es wird zur Reduktion der Komplexität bewusst auf Fragments, ViewModels und Tests verzichtet -- sobald Sie hier durch sind, sind das Ihre nächsten Themen :)

## Basics
Diese Beispiele adressieren Probleme, die Sie bei fast allen Apps haben.

### Erste App: BMI (fertig)
- Erklärvideo unter https://youtu.be/IiWxzRFJT9E
- Unterverzeichnis von examples: BMI
- Einführung in Android Studio inkl. Debugger, Refactoring, extract string ressource, ...
- Oberfläche mit LinearLayout, TextView, EditView und Button
- Implementierung eines EventHandlers unter Verwendung der Lambda-Notation
- setError, findViewById
- Java-Grundlagen inkl. Integer.parseInt, String.format, isEmpty
- weiterführende Links
  - https://developer.android.com/guide/components/fundamentals
  - https://developer.android.com/training/basics/firstapp
  - https://developer.android.com/guide/components/activities/intro-activities?hl=en

### Kommunikation zwischen Aktivitäten (fertig)
- Erklärvideo unter https://youtu.be/IjZ2TaGJV8U
- Unterverzeichnis von examples: ActivityCommunication
- Activity life-cycle
  - https://developer.android.com/guide/components/activities/activity-lifecycle
- Serializable
  - https://developer.android.com/reference/java/io/Serializable
- Intent
  - https://developer.android.com/guide/components/intents-filters
- Aufruf und Übergabe von Daten zwischen Activities
  - https://developer.android.com/training/basics/firstapp/starting-activity#java
- zurück implementieren
- verwendete (spezielle) Methoden: startActivity, startActivityForResult, getSerializableExtra, setResult, finish, Überschreiben von onActivityResult und onSupportNavigateUp, setDisplayHomeAsUpEnabled, setDisplayShowHomeEnabled


### Resources, Styling und Mehrsprachigkeit (fertig)
- Erklärvideo unter https://youtu.be/bfwhN9O9i6Q
- - Unterverzeichnis von examples: DemoResources
- Resourcen
  - https://developer.android.com/guide/topics/resources/providing-resources.html
- Mehrsprachigkeit
  - https://developer.android.com/distribute/best-practices/launch/localization-checklist
  - https://developer.android.com/guide/topics/resources/multilingual-support
- Density Buckets
  - https://developer.android.com/training/multiscreen/screendensities
  - dp/dpi: https://www.youtube.com/watch?v=tE3ox0RqfZ8
- Bilder/Icon/Drawables
  - https://developer.android.com/guide/topics/graphics/drawables 
  - https://developer.android.com/guide/topics/resources/drawable-resource#Shape
- Farben
  - https://material.io/develop/android/theming/color
  - Werkzeuge
    - https://material.io/resources/color
	- https://www.materialpalette.com
	- https://contrastchecker.online/
- Style
  - https://developer.android.com/guide/topics/resources/style-resource
- Theme
  - https://developer.android.com/guide/topics/ui/look-and-feel/themes
- Fonts
  - https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml
  - https://fonts.google.com/
- Material Design
  - https://material.io/
  - https://material.io/develop/android/docs/getting-started
  - Farben: https://material.io/design/color/the-color-system.html#color-theme-creation
  - labs: https://codelabs.developers.google.com/?cat=Design
  - Icons https://github.com/google/material-design-icons

### Layout Herangehensweise und Barrierefreiheit (fertig)
- Erklärvideo unter https://youtu.be/_YuaVZQeD_o
- Barrierefreiheit
  - https://developer.android.com/guide/topics/ui/accessibility
  - Lab: https://codelabs.developers.google.com/codelabs/basic-android-accessibility/#0
  - Scanner https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor
  - konkrete Maßnahmen in Android, z.B. 
    - EditText: android:hint
    - color contrast, mindestens 4.5, Check mit https://contrast-ratio.com
    - Mindesttextgröße: 12 sp
    - Hit-size mindestens 48dp (minWidth, minHeight) 
    - Für imageview,button u.ä. android:contentDescription bzw. android:importantForAccessibility="no"
- Herangehensweise
  - Flucht- und Symmetrielinien identifizieren / definieren
  - Anzahl der Fluchtlinien klein halten, insbesondere vertikale
  - "gutes Design braucht keine (gezeichneten) Linien" -- Freiräume gezielt einsetzen
- Layout-Editor: https://developer.android.com/studio/write/layout-editor
- Motioin-Editor: https://developer.android.com/studio/write/motion-editor
- Gutes Buch mit vielen Beispielen: Pixel Perfect Precision Handbook
  - Artikel dazu mit Link zum PDF des Buchs https://www.ustwo.com/blog/the-pixel-perfect-precision-handbook
- Guter Artikel mit vielen Grundlagen https://www.smashingmagazine.com/2018/02/comprehensive-guide-to-mobile-app-design/

### LinearLayout & ConstraintLayout (fertig)
- Erklärvideo unter https://youtu.be/1U5p69lQ-Ek
- - Unterverzeichnis von examples: LayoutDemos
- https://dribbble.com/shots/popular/mobile
- Layout (ViewGroup)
  - https://developer.android.com/guide/topics/ui/declaring-layout 
  - Beispielcode ist in DemoResources
- LinearLayout
  - https://developer.android.com/guide/topics/ui/layout/linear
- ConstraintLayout
  - https://developer.android.com/training/constraint-layout
  - https://www.youtube.com/watch?v=hqEfshM5Vfw&list=PLWz5rJ2EKKc8oNHPWtq4_FUzJtzqFNb7e

### AlertDialog, Toasts und Picker (fertig)
- Erklärvideo unter https://youtu.be/lMHtftFWKz0
- Unterverzeichnis von examples: DemoDialogs
- Toasts
  - https://developer.android.com/guide/topics/ui/notifiers/toasts
- AlertDialog
  - https://developer.android.com/guide/topics/ui/dialogs#AlertDialog
- Picker
  - https://developer.android.com/guide/topics/ui/controls/pickers

### RecyclerView (fertig)
- Erklärvideo unter https://youtu.be/krHARBTzVWw
- Unterverzeichnis von examples: DemoRecyclerView
- Links
  - Code
    - https://developer.android.com/guide/topics/ui/layout/recyclerview 
    - https://guides.codepath.com/android/using-the-recyclerview
  - Visualisierung/Erläuterung
    - https://www.youtube.com/watch?v=-VPM6ICgCk8
	- https://android.jlelse.eu/understanding-recyclerview-components-part-2-1fd43001a98f


### AppData mit Fokus auf SharedPreferences und SettingsActivity (fertig)
- Erklärvideo unter https://youtu.be/hxuVaWnF4-g
- Unterverzeichnis von examples: DemoSettings
- AppData & Files
  - Überblick: https://developer.android.com/guide/topics/data
  - Use Cases & best practices: https://developer.android.com/training/data-storage/use-cases
  - Beispiele von Android Developer in Kotlin (Kotlin to Java: Tools >> Kotlin>> Decompile Kotlin to Java)
    - https://developer.android.com/guide/topics/providers/document-provider
- File
  - Internal Storage (nur diese App hat Zugriff, i.A. auch nicht sichtbar für Dateimanager)
  - "External" Storage (Achtung ab Q/Android 10 "scoped access", vgl. https://www.youtube.com/watch?v=UnJ3amzJM94 -- gedacht für größere Dateien)
  - Shared Storage (Daten auch für andere Apps sichtbar/verfügbar, vorgesehen für Docs und Media)
  - https://developer.android.com/training/data-storage/app-specific
  - Überblick, was wann gelöscht wird: https://developer.android.com/training/data-storage?hl=en
  - JavaIO allgemein: http://tutorials.jenkov.com/java-io/overview.html
- SharedPreferences (Key-Value)
  - https://developer.android.com/training/data-storage/shared-preferences	
  - Settings: https://developer.android.com/guide/topics/ui/settings
- Database (vgl. andere Folge)
- ContentProvider (Zugriff von anderen Apps)
  - https://developer.android.com/guide/topics/providers/content-providers
- Cloud

### BottomNavigation, NavigationDrawer, OptionsMenu (fertig)
- Erklärvideo unter https://youtu.be/s_PHHvNRJTU
- Unterverzeichnis von examples: DemoBottomNavigation und DemoToolbarNavigation
- BottomNavigationView in DemoBottomNavigation
  - Folgende Ressourcen wie im Beispielprojekt hinzufügen/ergänzen:
    - Menu (ggf. Rechtsklick auf res und new android resource directory auswählen, hier menu anlegen): bottom_nav_menu
    - Values: dimens, styles, strings
    - Drawable: Icons runterladen z.B. von https://github.com/google/material-design-icons  dort Unterverzeichnis drawable-anydpi-v21 wählen
  - MainActivity bzw. alle Activities mit BottomNavigation (eigentlich hier besser Fragments)
    - Im Layout der Activtiy BottomNavigationView einfügen
    - Implements BottomNavigationView.OnNavigationItemSelectedListener
    - In onCreate Listener hinzufügen
- ToolBar (Teil von DemoToolbarNavigation)
  - Folgende Ressourcen wie im Beispielprojekt hinzufügen/ergänzen:
    - Layout: activity_main, app_bar_main, content_main
    - Values: dimens, styles, strings
  - Manifest: style für MainActivity setzen android:theme="@style/AppTheme.NoActionBar"
  - ActivityMain:
    - onCreate: Toolbar toolbar = findViewById(R.id.toolbar); setSupportActionBar(toolbar); 
- NavigationDrawer in DemoToolbarNavigation
  - braucht wie im Punkt davor beschrieben eine Toolbar
  - Folgende Ressourcen wie im Beispielprojekt hinzufügen/ergänzen:
    - Layout: activity_main (NavigationView dazu), nav_header_main
    - Menu (ggf. Rechtsklick auf res und new android resource directory auswählen, hier menu anlegen): activity_main_drawer
    - Values: dimens, styles, strings
    - Drawable: Icons entsprechend hinzufügen
  - ActivityMain:
    - implements NavigationView.OnNavigationItemSelectedListener
    - onCreate: Toggle, Toolbar etc 
- OptionsMenu (enthalten in DemoToolbarNavigation)
  - braucht wie im Punkt davor beschrieben eine Toolbar
  - Folgende Ressourcen wie im Beispielprojekt hinzufügen/ergänzen:
    - Menu (ggf. Rechtsklick auf res und new android resource directory auswählen, hier menu anlegen): main_options_menu -- im Menu app:iconTint passend wählen
    - Values: strings
    - Drawable: Icons entsprechend hinzufügen 
  - In zugehöriger Activity
    - onCreateOptionsMenu überschreiben und dort Menu mit Inflater: getMenuInflater().inflate(R.menu.main_options_menu, menu);

### async (fertig)
- Erklärvideo unter
- Thread und WorkManager sind im Projekt examples/DemoRest enthalten
- Video zu "dropped frames": https://www.youtube.com/watch?v=qk5F6Bxqhr4
- Entscheidungshilfen
  - https://android-developers.googleblog.com/2018/10/modern-background-execution-in-android.html
  - https://developer.android.com/guide/background/
- Threads
  - https://developer.android.com/guide/components/processes-and-threads 
  - Zugriff von anderen Thread auf UI-Thread mit Activity.runOnUiThread(Runnable), View.post(Runnable), View.postDelayed(Runnable, long)
  - Beispiel
  ```
  public void onClick(View v) {
        new Thread(() -> {
            // a potentially time consuming task
            final Bitmap bitmap = longTask("image.png");
            imageView.post(() -> imageView.setImageBitmap(bitmap));
        }).start();
    }
  ```
- WorkManager
  - Video: https://www.youtube.com/watch?v=pe_yqM16hPQ
  - Doku: https://developer.android.com/topic/libraries/architecture/workmanager
  - Lab: https://codelabs.developers.google.com/codelabs/android-workmanager-java/#0
  - Prinzip: 
    - Das WAS definieren
      - extend Worker
      - public Result doWork()  success/failure/retry
      - z.B. eine Klasse UpdateWorker extend Worker
    - Das WANN definieren
      - Bedingungen (constraints) https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work#work-constraints
      - Bei retry: wie häufig und wann wiederholen? https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work#retry_and_backoff_policy
      - Einmalig
	    - https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work#schedule_one-time_work
	    - OneTimeWorkRequest updateRequest =  new OneTimeWorkRequest.Builder(UpdateWorker.class).build();
      - oder periodisch https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work#schedule_periodic_work
      - Vor .build optional dazu: setInputData(...).setConstraints(...).setBackoffCriteria(...).build()
    - Zu Warteschlange hinzufügen
      - Als Kette: beginWith(…).then(…).then(…).enqueue()
      - einfach: WorkManager.getInstance(application).enqueue(updateRequest);
- ergänzende Bibliothek RxJava: https://github.com/ReactiveX/RxJava
- ab in die Tiefe: https://www.youtube.com/watch?v=UPq1LDxL5_w&feature=youtu.be
Sowohl Threads als auch den WorkManager werde ich in der Folge zu REST verwenden/aufgreifen.

### REST (in Planung)
- REST
  - https://www.programmableweb.com/api-university
- JSON
  - https://www.json.org  
  - Empfehlung: Verwendung von GSON https://github.com/google/gson
- Netzwerkverbindung mit Volley
  - https://developer.android.com/training/volley/index.html
- Manifest: internet permission

### Room (DB) (in Planung)
- Lab https://codelabs.developers.google.com/codelabs/android-room-with-a-view/index.html?index=..%2F..index#2

### Firebase (in Planung)
- Lab https://codelabs.developers.google.com/codelabs/firebase-android/index.html?index=..%2F..index#0

### Bibliotheken (Onboarding, Bilder, Charts, …) (in Planung)

## Zusammenfassung, Ergänzungen und Ausblick
- package by feature: https://www.techyourchance.com/popular-package-structures/
- Architektur, Fragments, ViewModel, Navigation Component, View Binding (one way) und Data Binding (two way), DependencyInjection
- umfangreiche DemoApp mit allen best practices https://github.com/android/sunflower
- https://developer.android.com/jetpack/guide
- https://developer.android.com/guide/components/fragments
- https://developer.android.com/topic/libraries/architecture/viewmodel
- https://developer.android.com/guide/navigation
- https://developer.android.com/guide/navigation/navigation-migrate
- https://www.techyourchance.com/
- Activities vs. Fragments: https://www.techyourchance.com/navigation-between-screens-android/ und https://github.com/xxv/android-lifecycle und https://www.techyourchance.com/android-fragment-lifecycle/ (Zitat: "Fragments are a mess. Their lifecycle is rocket science. The only thing worse than Fragments is their official documentation.")
- guides: https://developer.android.com/guide
 

