package com.example.movieslistapp.data

import com.example.movieslistapp.models.Movie
import com.example.movieslistapp.R

fun createMoviesList(): List<Movie> {
    val icon = listOf(
        R.drawable.action,
        R.drawable.science_fiction,
        R.drawable.romance,
        R.drawable.thriller,
        R.drawable.horror,
        R.drawable.other
    )

    val library = listOf(
        "The Godfather" to "An epic saga of the Corleone family, a Sicilian Mafia clan in New York City.",
        "The Shining" to "A struggling writer accepts a job as the caretaker at an isolated hotel over the winter with his wife and son, but becomes increasingly unstable and violent, putting his family in danger.",
        "Pulp Fiction" to "A nonlinear story that intertwines the paths of a washed-up boxer, a retired gangster, and a mobster's wife.",
        "Saving Private Ryan" to "A group of American soldiers venture behind enemy lines to rescue a paratrooper lost during World War II.",
        "Schindler's List" to "A German businessman saves the lives of over a thousand Jews during the Holocaust.",
        "The Lord of the Rings: The Return of the King" to "Frodo and his friends embark on a final quest to destroy the One Ring and defeat the Dark Lord Sauron.",
        "The Godfather Part II" to "The saga of the Corleone family continues as Vito Corleone reflects on his youth and Michael struggles to maintain control of the family empire.",
        "12 Angry Men" to "A jury of twelve men must decide the fate of a young man accused of murder.",
        "Star Wars: Episode IV - A New Hope" to "A group of rebels unites to fight against the evil Galactic Empire.",
        "Halloween" to "A group of teenagers is stalked by a masked killer on Halloween night.",
        "Jaws" to "A great white shark terrorizes tourists at a beach resort.",
        "Alien" to "The crew of a spaceship is attacked by a deadly alien creature.",
        "E.T. the Extra-Terrestrial" to "A boy befriends an alien lost on Earth.",
        "Back to the Future" to "A teenager accidentally travels back in time to 1955 and prevents his parents from meeting.",
        "Jurassic Park" to "A billionaire creates a theme park with real dinosaurs, but things quickly get out of control.",
        "The Lion King" to "A young lion must learn to take his place as king after the death of his father.",
        "Toy Story" to "A group of toys come to life when their human owners are not around.",
        "Titanic" to "A poor young man and a rich young woman fall in love aboard the ship Titanic, which tragically sinks in the Atlantic Ocean.",
        "Forrest Gump" to "A man with an IQ of 75 lives an extraordinary life and influences historical events.",
        "The Matrix" to "A computer hacker learns that the world he lives in is a simulation and joins a rebellion against the machines.",
        "The Lord of the Rings: The Fellowship of the Ring" to "Frodo Baggins is given the task of destroying the One Ring, an evil artifact that threatens Middle-earth.",
        "The Lord of the Rings: The Two Towers" to "Frodo and his friends continue their journey to destroy the One Ring, while Sauron, the Dark Lord, prepares for war.",
        "The Dark Knight" to "Batman faces his greatest enemy, the Joker, who plans to destroy Gotham City.",
        "Inception" to "A dream thief uses new technology to steal corporate secrets.",
        "Parasite" to "A poor family infiltrates a rich family and takes on various different jobs.",
        "1917" to "Two British soldiers are sent on a dangerous mission to deliver a message that could save the lives of their battalion mates."
    )


    val movies = library.mapIndexed { index, (name, description) ->
        Movie(
            pic = icon[index % icon.size],
            name = name,
            description = description,
            watched = false,
            rating = 0
        )
    }
    return movies
}