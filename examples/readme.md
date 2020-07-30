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

### LinearLayout & ConstraintLayout (fertig)
- Erklärvideo unter https://youtu.be/1U5p69lQ-Ek
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
- Toasts
  - https://developer.android.com/guide/topics/ui/notifiers/toasts
- AlertDialog
  - https://developer.android.com/guide/topics/ui/dialogs#AlertDialog
- Picker
  - https://developer.android.com/guide/topics/ui/controls/pickers

### RecyclerView (fertig)
- Erklärvideo unter https://youtu.be/krHARBTzVWw
- Links
  - Code
    - https://developer.android.com/guide/topics/ui/layout/recyclerview 
    - https://guides.codepath.com/android/using-the-recyclerview
  - Visualisierung/Erläuterung
    - https://www.youtube.com/watch?v=-VPM6ICgCk8
	- https://android.jlelse.eu/understanding-recyclerview-components-part-2-1fd43001a98f


### AppData mit Fokus auf SharedPreferences und SettingsActivity (fertig)
- Erklärvideo unter https://youtu.be/hxuVaWnF4-g
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
- SharedPreferences (Key-Value)
  - https://developer.android.com/training/data-storage/shared-preferences	
  - Settings: https://developer.android.com/guide/topics/ui/settings
- Database (vgl. andere Folge)
- ContentProvider (Zugriff von anderen Apps)
  - https://developer.android.com/guide/topics/providers/content-providers
- Cloud

### BottomNavigation, NavigationDrawer, OptionsMenu (fertig)
- Erklärvideo unter https://youtu.be/Qe_QQAkJGRs
- BottomNavigationView
  - Folgende Ressourcen wie im Beispielprojekt hinzufügen/ergänzen:
    - Menu (ggf. Rechtsklick auf res und new android resource directory auswählen, hier menu anlegen): bottom_nav_menu
    - Values: dimens, styles, strings
    - Drawable: Icons runterladen z.B. von https://github.com/google/material-design-icons  dort Unterverzeichnis drawable-anydpi-v21 wählen – Farben mit primary … anpassen
  - MainActivity bzw. alle Activities mit BottomNavigation (eigentlich hier besser Fragments)
    - Im Layout der Activtiy BottomNavigationView einfügen
    - Implements BottomNavigationView.OnNavigationItemSelectedListener
    - In onCreate Listener hinzufügen
- ToolBar
  - Folgende Ressourcen wie im Beispielprojekt hinzufügen/ergänzen:
    - Layout: activity_main, app_bar_main, content_main
    - Values: dimens, styles, strings
  - Manifest: style für MainActivity setzen android:theme="@style/AppTheme.NoActionBar"
  - ActivityMain:
    - onCreate: Toolbar toolbar = findViewById(R.id.toolbar); setSupportActionBar(toolbar); 
- NavigationDrawer
  - braucht wie im Punkt davor beschrieben eine Toolbar
  - Folgende Ressourcen wie im Beispielprojekt hinzufügen/ergänzen:
    - Layout: activity_main (NavigationView dazu), nav_header_main
    - Menu (ggf. Rechtsklick auf res und new android resource directory auswählen, hier menu anlegen): activity_main_drawer
    - Values: dimens, styles, strings
    - Drawable: Icons entsprechend hinzufügen
  - ActivityMain:
    - implements NavigationView.OnNavigationItemSelectedListener
    - onCreate: Toggle, Toolbar etc 
- OptionsMenu
  - braucht wie im Punkt davor beschrieben eine Toolbar
  - Folgende Ressourcen wie im Beispielprojekt hinzufügen/ergänzen:
    - Menu (ggf. Rechtsklick auf res und new android resource directory auswählen, hier menu anlegen): main_options_menu
    - Values: strings
    - Drawable: Icons entsprechend hinzufügen -- Farben beachten
  - In zugehöriger Activity
    - onCreateOptionsMenu überschreiben und dort Menu mit Inflater:	getMenuInflater().inflate(R.menu.main_options_menu, menu);
	
## Extras

### Room (DB) (in Planung)

### Firebase (in Planung)

### REST (in Planung)

### Bibliotheken (Onboarding, Bilder, Charts, …) (in Planung)

## Ausblick
Architektur, Fragments, ViewModel, Navigation Component
- https://developer.android.com/jetpack/guide
- https://developer.android.com/guide/components/fragments
- https://developer.android.com/topic/libraries/architecture/viewmodel
- https://developer.android.com/guide/navigation
- https://developer.android.com/guide/navigation/navigation-migrate

 

