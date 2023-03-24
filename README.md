## HackerNewsController API Documentation

### Description

This is a RESTful API controller for the HackerNews service. The API provides endpoints to retrieve top stories, past stories, and comments related to a particular story.

### Base URL

http://localhost:8085/

### Endpoints

#### 1. Retrieve Top Stories Details

* **URL:** `/top-stories`
* **Method:** `GET`
* **Description:** Returns top 10 stories ranked by the score in last 15 minutes.


#### 2. Retrieve All Past Stories

* **URL:** `/past-stories`
* **Method:** `GET`
* **Description:** Returns all stories previously served by top-stories.


#### 3. Retrieve Comments for a Story

* **URL:** `/comments/{storyId}`
* **Method:** `GET`
* **Description:** Returns a list of Comment objects containing comment text and username related to a particular story.


#### 4. How to run Hacker News API using Docker




