package com.example.screenplay.utils

import com.example.screenplay.R
import com.example.screenplay.data.entity.MovieEntity
import com.example.screenplay.data.entity.TvShowEntity

object DataDummy {
    private val movieTitles = arrayOf(
        "A Star Is Born",
        "Alita: Battle Angel",
        "Aquaman",
        "Bohemian Rhapsody",
        "Cold Pursuit",
        "Creed II",
        "Fantastic Beasts: The Crimes of Grindelwald",
        "Glass",
        "How to Train Your Dragon: The Hidden World",
        "Avengers: Infinity War"
    )

    private val moviePosters = arrayOf(
        R.drawable.poster_a_start_is_born,
        R.drawable.poster_alita,
        R.drawable.poster_aquaman,
        R.drawable.poster_bohemian,
        R.drawable.poster_cold_persuit,
        R.drawable.poster_creed,
        R.drawable.poster_crimes,
        R.drawable.poster_glass,
        R.drawable.poster_how_to_train,
        R.drawable.poster_infinity_war
    )

    private val movieDirectors = arrayOf(
        "Bradley Cooper",
        "Robert Rodriguez",
        "James Wan",
        "Bryan Singer",
        "Hans Petter Moland",
        "Steven Caple Jr.",
        "David Yates",
        "M. Night Shyamalan",
        "Dean DeBlois",
        "Joe Russo, Anthony Russo"
    )

    private val movieReleaseYears = arrayOf(
        2018,
        2019,
        2018,
        2018,
        2019,
        2018,
        2018,
        2019,
        2019,
        2018
    )

    private val movieGenres = arrayOf(
        "Roman/Musikal",
        "Laga/Fiksi Ilmiah",
        "Laga/Petualangan",
        "Musikal/Drama",
        "Laga/Cerita seru",
        "Olahraga/Drama",
        "Fantasi/Petualangan",
        "Cerita seru/Drama",
        "Animasi/Anak-anak",
        "Laga/Fiksi ilmiah"
    )

    private val movieSynopsis = arrayOf(
        "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
        "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
        "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
        "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
        "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
        "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
        "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
        "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
        "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
        "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
    )

    val dummyMovies: ArrayList<MovieEntity>
        get(){
            val list = arrayListOf<MovieEntity>()
            for (position in movieTitles.indices){
                val movie = MovieEntity(
                    id = position,
                    title = movieTitles[position],
                    poster = moviePosters[position],
                    director = movieDirectors[position],
                    releaseYear = movieReleaseYears[position],
                    synopsis = movieSynopsis[position]
                )
                list.add(movie)
            }
            return list
        }

    private val tvTitles = arrayOf(
        "Arrow",
        "Doom Patrol",
        "Family Guy",
        "The Flash",
        "Game of Thrones",
        "Gotham",
        "Hanna",
        "Marvel's Iron Fist",
        "Naruto Shippūden",
        "NCIS"
    )

    private val tvPosters = arrayOf(
        R.drawable.poster_arrow,
        R.drawable.poster_doom_patrol,
        R.drawable.poster_family_guy,
        R.drawable.poster_flash,
        R.drawable.poster_god,
        R.drawable.poster_gotham,
        R.drawable.poster_hanna,
        R.drawable.poster_iron_fist,
        R.drawable.poster_naruto_shipudden,
        R.drawable.poster_ncis
    )

    private val tvCreators = arrayOf(
        "Greg Berlanti, Marc Guggenheim, Andrew Kreisberg",
        "Jeremy Carver",
        "Seth MacFarlane",
        "Greg Berlanti, Geoff Johns, Andrew Kreisberg",
        "David Benioff, D. B. Weiss",
        "Bruno Heller",
        "David Farr",
        "Scott Buck",
        "Masashi Kishimoto",
        "Donald P. Bellisario, Don McGill"
    )

    private val tvReleaseYears = arrayOf(
        2012,
        2019,
        1999,
        2014,
        2011,
        2014,
        2019,
        2017,
        2007,
        2003
    )

    private val tvGenres = arrayOf(
        "Crime, Drama, Mystery, Action & Adventure",
        "Sci-Fi & Fantasy, Action & Adventure",
        "Animation, Comedy",
        "Drama, Sci-Fi & Fantasy",
        "Sci-Fi & Fantasy, Drama",
        "Drama, Fantasy, Crime",
        "Action & Adventure, Drama",
        "Action & Adventure, Drama, Sci-Fi & Fantasy",
        "Animation, Comedy, Drama",
        "Action & Adventure, Crime, Drama"
    )

    private val tvSynopsis = arrayOf(
        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
        "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
        "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
        "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
        "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
        "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
        "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
        "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
        "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
        "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position."
    )

    private val tvNSeasons = arrayOf(
        8,
        2,
        18,
        6,
        8,
        5,
        2,
        2,
        20,
        17
    )

    val dummyTvShows: ArrayList<TvShowEntity>
        get(){
            val list = arrayListOf<TvShowEntity>()
            for (position in tvTitles.indices){
                val tvShow = TvShowEntity(
                    id = position,
                    title = tvTitles[position],
                    poster = tvPosters[position],
                    creator = tvCreators[position],
                    releaseYear = tvReleaseYears[position],
                    genre = tvGenres[position],
                    synopsis = tvSynopsis[position],
                    nSeasons = tvNSeasons[position]
                )
                list.add(tvShow)
            }
            return list
        }
}