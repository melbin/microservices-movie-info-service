package sv.edu.uesocc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sv.edu.uesocc.dto.Movie;
import sv.edu.uesocc.dto.MovieSummary;

@RestController
@RequestMapping("/movieInfo/")
public class MovieResourceController {

	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
	public Movie getMovie(@PathVariable("movieId") String movieId) {

		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey,
				MovieSummary.class);
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}
	
}
