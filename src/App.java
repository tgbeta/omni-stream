import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        
        //String imdbKey = System.getenv(name:"IMDB_APY_KEY");
        //250 best movies
        //String url = "https://imdb-api.com/en/API/Top250Movies/" + imdbKey;
        
        //HTTP connection to search top movies
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //Extract data
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Show data
        for (int i = 0; i < listaDeFilmes.size(); i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
            System.out.println("\u001b[1mTitle: \u001b[1m" + filme.get("title"));
            System.out.println("\u001b[1mUrl da imagem: \u001b[m" + filme.get("image"));
            double rating = Double.parseDouble(filme.get("imDbRating"));
            int starNumber = (int) rating;

            for (int n = 0; n < starNumber; n++) {
                System.out.print("⭐");
            }
            System.out.println();
        }
    }
}
