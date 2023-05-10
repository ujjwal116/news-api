This application exposes endpoint:
http://localhost:8080/news/api?query=tesla&fromDate=2023-04-13&sortBy=publishedAt

## Mandatory parameters
1. Query
2. fromDate
3. sortBy

## Configuration:
The app can be configured with:
1. the source endpoint as"hostUrl".
2. the maximum number of articles in response as maxResults".
3. API key should be provided via environment variable as"apiKey=xxxx" .