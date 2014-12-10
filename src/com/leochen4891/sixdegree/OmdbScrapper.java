package com.leochen4891.sixdegree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class OmdbScrapper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//@formatter:off
		/*
		Example request:
		http://www.omdbapi.com/?t=The+Shawshank+Redemption&y=&plot=short&r=json

		Example response:
		{
			"Title":"The Shawshank Redemption",
			"Year":"1994",
			"Rated":"R",
			"Released":"14 Oct 1994",
			"Runtime":"142 min",
			"Genre":"Crime, Drama",
			"Director":"Frank Darabont",
			"Writer":"Stephen King (short story \"Rita Hayworth and Shawshank Redemption\"), Frank Darabont (screenplay)",
			"Actors":"Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler",
			"Plot":"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
			"Language":"English",
			"Country":"USA",
			"Awards":"Nominated for 7 Oscars. Another 16 wins & 16 nominations.",
			"Poster":"http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX300.jpg",
			"Metascore":"80",
			"imdbRating":"9.3",
			"imdbVotes":"1,330,582",
			"imdbID":"tt0111161",
			"Type":"movie",
			"Response":"True"
		}
		*/
		//@formatter:on

		// compose the request
		String title = "";
		String year = "";
		String plot = "short";
		String r = "json";
		StringBuilder sb;
		StringBuilder ret;

		Random rand = new Random();
		for (int i = 0; i < movieNames.length; i++) {
			System.out.print(i + ":" + movieNames[i] + "...");
			Writer writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("movies/movie_" + i + ".txt"),
						"utf-8"));
				sb = new StringBuilder();
				ret = new StringBuilder();
				title = URLEncoder.encode(movieNames[i], "UTF-8");
				sb.append("http://www.omdbapi.com/?");
				sb.append("t=").append(title).append("&");
				sb.append("y=").append(year).append("&");
				sb.append("plot=").append(plot).append("&");
				sb.append("r=").append(r);
				CloseableHttpClient client = HttpClients.createDefault();
				String url = sb.toString();
				HttpGet get = new HttpGet(url);
				HttpResponse response = client.execute(get);
				BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),
						"UTF8"));

				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					writer.write(line);
					line = reader.readLine();
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (null != writer) {
						writer.flush();
						writer.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(" done");

			try {
				Thread.sleep(1000 + rand.nextInt(5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(" - - - - Task Finished - - - -");
	}

	//@formatter:off
	private static String[] movieNames = { 
		"The Shawshank Redemption",
		"The Godfather",
		"The Godfather: Part II",
		"The Dark Knight",
		"Pulp Fiction",
		"The Good, the Bad and the Ugly",
		"12 Angry Men",
		"Schindler's List",
		"The Lord of the Rings: The Return of the King",
		"Fight Club",
		"The Lord of the Rings: The Fellowship of the Ring",
		"Star Wars: Episode V - The Empire Strikes Back",
		"Inception",
		"Forrest Gump",
		"Interstellar",
		"One Flew Over the Cuckoo's Nest",
		"The Lord of the Rings: The Two Towers",
		"Goodfellas",
		"The Matrix",
		"Star Wars: Episode IV - A New Hope",
		"Seven Samurai",
		"City of God",
		"Se7en",
		"The Usual Suspects",
		"The Silence of the Lambs",
		"It's a Wonderful Life",
		"Once Upon a Time in the West",
		"Léon: The Professional",
		"Life Is Beautiful",
		"Casablanca",
		"Raiders of the Lost Ark",
		"American History X",
		"Psycho",
		"City Lights",
		"Saving Private Ryan",
		"Rear Window",
		"Spirited Away",
		"The Intouchables",
		"Modern Times",
		"Terminator 2: Judgment Day",
		"Memento",
		"The Pianist",
		"The Green Mile",
		"Sunset Blvd.",
		"Apocalypse Now",
		"Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb",
		"The Departed",
		"Gladiator",
		"Back to the Future",
		"Alien",
		"The Prestige",
		"The Great Dictator",
		"The Lives of Others",
		"The Lion King",
		"The Dark Knight Rises",
		"Django Unchained",
		"The Shining",
		"Cinema Paradiso",
		"Paths of Glory",
		"American Beauty",
		"WALL·E",
		"North by Northwest",
		"Aliens",
		"Citizen Kane",
		"Amélie",
		"Vertigo",
		"M",
		"Toy Story 3",
		"Das Boot",
		"Oldboy",
		"Grave of the Fireflies",
		"Princess Mononoke",
		"Star Wars: Episode VI - Return of the Jedi",
		"Once Upon a Time in America",
		"A Clockwork Orange",
		"Taxi Driver",
		"Reservoir Dogs",
		"Double Indemnity",
		"Requiem for a Dream",
		"Braveheart",
		"To Kill a Mockingbird",
		"Lawrence of Arabia",
		"Witness for the Prosecution",
		"Boyhood",
		"Eternal Sunshine of the Spotless Mind",
		"Gone Girl",
		"Full Metal Jacket",
		"Singin' in the Rain",
		"The Sting",
		"Bicycle Thieves",
		"Amadeus",
		"Monty Python and the Holy Grail",
		"Snatch.",
		"Rashomon",
		"L.A. Confidential",
		"All About Eve",
		"The Apartment",
		"For a Few Dollars More",
		"The Kid",
		"2001: A Space Odyssey",
		"Some Like It Hot",
		"The Treasure of the Sierra Madre",
		"Inglourious Basterds",
		"The Third Man",
		"Indiana Jones and the Last Crusade",
		"A Separation",
		"Yôjinbô",
		"Batman Begins",
		"Toy Story",
		"Metropolis",
		"Unforgiven",
		"Raging Bull",
		"Taare Zameen Par",
		"Scarface",
		"Chinatown",
		"Up",
		"Downfall",
		"The Great Escape",
		"Die Hard",
		"Mr. Smith Goes to Washington",
		"On the Waterfront",
		"Pan's Labyrinth",
		"3 Idiots",
		"Heat",
		"The Hunt",
		"The Bridge on the River Kwai",
		"Good Will Hunting",
		"The Seventh Seal",
		"The Wolf of Wall Street",
		"My Neighbor Totoro",
		"Ikiru",
		"The Gold Rush",
		"Guardians of the Galaxy",
		"Wild Strawberries",
		"The Elephant Man",
		"Ran",
		"The General",
		"Blade Runner",
		"Lock, Stock and Two Smoking Barrels",
		"The Secret in Their Eyes",
		"Gran Torino",
		"Casino",
		"The Big Lebowski",
		"Rebecca",
		"Warrior",
		"V for Vendetta",
		"Howl's Moving Castle",
		"The Deer Hunter",
		"Rang De Basanti",
		"It Happened One Night",
		"Cool Hand Luke",
		"How to Train Your Dragon",
		"Fargo",
		"Trainspotting",
		"Rush",
		"Judgment at Nuremberg",
		"Gone with the Wind",
		"The Maltese Falcon",
		"Into the Wild",
		"Dil Chahta Hai",
		"The Wages of Fear",
		"A Beautiful Mind",
		"Dial M for Murder",
		"The Sixth Sense",
		"Hotel Rwanda",
		"The Thing",
		"Finding Nemo",
		"No Country for Old Men",
		"Butch Cassidy and the Sundance Kid",
		"Kill Bill: Vol. 1",
		"Platoon",
		"Mary and Max",
		"Life of Brian",
		"12 Years a Slave",
		"Incendies",
		"Network",
		"Touch of Evil",
		"Diabolique",
		"Annie Hall",
		"Sin City",
		"The Princess Bride",
		"The Grand Budapest Hotel",
		"Stand by Me",
		"There Will Be Blood",
		"Ben-Hur",
		"Gangs of Wasseypur",
		"Amores Perros",
		"The 400 Blows",
		"The Grapes of Wrath",
		"The Wizard of Oz",
		"In the Name of the Father",
		"Hachi: A Dog's Tale",
		"Million Dollar Baby",
		"X-Men: Days of Future Past",
		"Persona",
		"The Best Years of Our Lives",
		"The Avengers",
		"8½",
		"The Bourne Ultimatum",
		"Nausicaä of the Valley of the Wind",
		"Gandhi",
		"Donnie Darko",
		"Strangers on a Train",
		"Infernal Affairs",
		"Jaws",
		"High Noon",
		"Stalker",
		"Twelve Monkeys",
		"Notorious",
		"The King's Speech",
		"Shutter Island",
		"Fanny and Alexander",
		"The Terminator",
		"Lagaan: Once Upon a Time in India",
		"The Battle of Algiers",
		"Harry Potter and the Deathly Hallows: Part 2",
		"Groundhog Day",
		"Before Sunrise",
		"Ip Man",
		"The Night of the Hunter",
		"Rocky",
		"Dog Day Afternoon",
		"Monsters, Inc.",
		"La Haine",
		"La Strada",
		"Who's Afraid of Virginia Woolf?",
		"Memories of Murder",
		"Barry Lyndon",
		"Pirates of the Caribbean: The Curse of the Black Pearl",
		"The Big Sleep",
		"Castle in the Sky",
		"A Fistful of Dollars",
		"The Truman Show",
		"The Help",
		"The Graduate",
		"Roman Holiday",
		"Jurassic Park",
		"The Hustler",
		"Swades",
		"The Celebration",
		"In the Mood for Love",
		"Beauty and the Beast",
		"Her",
		"Stalag 17",
		"Papillon",
		"Rope",
		"Before Sunset",
		"Spring, Summer, Fall, Winter... and Spring",
		"Paris, Texas",
		"The Killing"
	};
	//@formatter:on

}
