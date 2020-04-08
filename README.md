# Android-Exercise-v2.1

- minSdkVersion 14
- targetSdkVersion 28

# Assumptions:
- Using recyclerview instead of ListView, simply because it's more powerful and easy to use.
- There is no dependency injection (e.g dagger), I believe it's a overkill for this exercise.
- Some of the data contain null(images, title and description).
- Error message is toast message only (there is no ui/instructions for error handling).
- Network check in hand over to okhttp and retrofit.
- Image offline caching is handle by Glide. And there is no api data caching due to the fact I'm assuming the data source will change, otherwise I should download the file instead of makeing a api call.
- Orientation change is handle by stop activity from restarting, unless there is a reason to restart activity?
- There is no unit testing, due to the fact that the scope is unclear and there is no CI/CD pipeline setup
- Instrumented test tests can added upon request
