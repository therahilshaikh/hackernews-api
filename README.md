## HackerNewsController API Documentation

### Description

This is a RESTful API controller for the HackerNews service. The API provides endpoints to retrieve top stories, past stories, and comments related to a particular story.

### Base URL

http://localhost:8085/

### Endpoints

### 1. Retrieve Top Stories Details

* **URL:** `/top-stories`
* **Method:** `GET`
* **Description:** Returns top 10 stories ranked by the score in last 15 minutes.


### 2. Retrieve All Past Stories

* **URL:** `/past-stories`
* **Method:** `GET`
* **Description:** Returns all stories previously served by top-stories.


### 3. Retrieve Comments for a Story

* **URL:** `/comments/{storyId}`
* **Method:** `GET`
* **Description:** Returns a list of Comment objects containing comment text and username related to a particular story.



### 4. How to run Hacker News API using Docker


1. Make sure you have Docker installed on your machine. 
2. Clone the GitHub repository for the Hacker News API to your local machine:

    ```bash
    git clone https://github.com/therahilshaikh/hackernews-api.git
    ```

3. Navigate to the root directory of the cloned repository:

    ```bash
    cd hackernews-api
    ```

4. Run the Docker container by using the `docker-compose up` command:

    ```bash
    docker-compose up
    ```

    This command will start the container and run the Hacker News API on `localhost:8085`.

6. You can now access the API by opening a web browser and entering the following URL:

    ```arduino
    http://localhost:8085
    ```

    You should see a message indicating that the API is running successfully.

7. To stop the container, use the `docker-compose down` command:

    ```bash
    docker-compose down
    ```

    This command will stop and remove the container.

That's it! You have successfully run the Hacker News API using Docker.
