## Spotify Docker Client Playground Project

This project was built to play around with Spotify's Docker Client. The Docker Client open-sourced by Spotify is essentially an HTTP client for the Docker API. 
It just wraps Docker functionality in a custom Restful interface to manipulate the client "on-demand". See the RestControllers in the com.hxs.web package for end points.

### Requirements
- Java 8
- Docker installed locally in Swarm Mode
    - `docker swarm init`