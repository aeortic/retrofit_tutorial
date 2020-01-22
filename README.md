# retrofit_tutorial

Based on https://medium.com/@prakash_pun/retrofit-a-simple-android-tutorial-48437e4e5a23

Uses http://jsonplaceholder.typicode.com/ for the rest API emulation

The new files will be placed in this structure:

```
Project
├- adapter
|   └ CustomAdapter
|
├- model
|   └ RetroPhoto
|
├- network
    ├ GetDataService (interface)
    └ RetrofitClientInstance

Res
└ custom_row.xml
```

The following libraries will be used:
```
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'com.squareup.picasso:picasso:2.5.2'
    implementation 'com.squareup.retrofit2:retrofit:2.3.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation 'com.jakewharton.picasso:picasso2-okhttp3-downloader:1.1.0'
```

The ideal order of commits would look like this list:
1. Add libraries
2. Add internet permission
3. Create model
4. Create retrofit instance
5. Define endpoints
6. Create a custom row layout file
7. Add a recycler view to the main layout file
8. Create a custom adapter
9. Initialize the data service, and enqueue a get request
