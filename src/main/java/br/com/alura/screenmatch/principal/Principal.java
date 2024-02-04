package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=7eeb4820";
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private SerieRepository repository;
    private List<Serie> series = new ArrayList<>();

    private Optional<Serie> serieBusca;

    public Principal(SerieRepository repository) {
        this.repository = repository;
    }


    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1- Buscar séries
                    2- Buscar episódios
                    3- Listar séries buscadas
                    4- Buscar serie por titulo
                    5- Buscar sinopse da série
                    6- Buscar series por ator
                    7- Buscar Top 5 Serie
                    8- Buscar Serie por categoria
                    9- Buscar Serie para Maratonar
                    10- Buscar Serie por trecho
                    11- Buscar Top 5 Episodios Por Serie
                    12- Buscar Episodios por data
                                    
                    0- Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSinopseSerie();
                    break;
                case 6:
                    buscarSeriePorAtor();
                    break;
                case 7:
                    buscarTop5Series();
                    break;
                case 8:
                    buscarSeriePorCategoria();
                    break;
                case 9:
                    buscarSeriePorCategoriaTemporadaAvalicao();
                    break;
                case 10:
                    buscarEpisodioPorTrecho();
                    break;
                case 11:
                    topEpisodiosPorSerie();
                    break;
                case 12:
                    buscarEpisodiosDepoisDeUmaData();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }

        //---------------------------//----------------------------------------//-----------------------------------------//---
        //CURSO ANTERIOR
//        System.out.println("Digite o nome da série para buscar.");
//        var nomeSerie = leitura.nextLine();
//        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
//        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
//        System.out.println(dados);
//
//
//        List<DadosTemporada> temporadas = new ArrayList<>();
//
//        for (int i = 1; i <= dados.totalTemporada(); i++) {
//            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
//            DadosTemporada temporada = converteDados.obterDados(json, DadosTemporada.class);
//            temporadas.add(temporada);
//        }
//        temporadas.forEach(System.out::println);
//-----------------------------------//----------------------------------------------------//--------------------------
//        for (int i = 0; i< dados.totalTemporada(); i++){
//            List<DadosEpisodio> episodioTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodioTemporada.size(); j++){
//                System.out.println(episodioTemporada.get(j).titulo());
//            }
//        }
//------------------------------//----------------------------------------//-------------------------------------------
        //CURSO ANTERIOR
//        #Operação Lambda
//        Temporada((parametro) -> expressao)
//        Episodio((parametro) -> expressao)
//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
//
//        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream())
//                .collect(Collectors.toList());

//        #Peek
//        System.out.println("\nTop 10 episódios");
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro filtro(N/A) " + e))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .peek(e -> System.out.println("Ordenação " + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limite " + e))
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);
//        System.out.println("\n");

//        #Stream
//        List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");
//        nomes.stream()
//                .sorted()
//                .limit(3)
//                .filter(n -> n.startsWith("N"))
//                .map(n -> n.toUpperCase())
//                .forEach(System.out::println);
//------------------------------------------//----------------------------------------------------//--------------------

        //CURSO ANTERIOR
//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream()
//                        .map(d -> new Episodio(t.numero(), d)))
//                .collect(Collectors.toList());
//
//        episodios.forEach(System.out::println);
//
//        System.out.println("\nDigite um trecho por episodio.");
//        var trechoTitulo = leitura.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                .findFirst();
//
//        if (episodioBuscado.isPresent()) {
//            System.out.println("Episodio encontrado!");
//            System.out.println("Temporada: " + episodioBuscado.get());
//        } else {
//            System.out.println("Episódio não encontrado!");
//        }

//-----------------------------------------//--------------------------------------------------//-----------------------

//
//        System.out.println("A partir de que ano você deseja ver os episodios?");
//        var ano = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println("Temporada: " + e.getDataLancamento() +
//                        " Episodio: " + e.getTitulo() +
//                        " Data lançamento: " + e.getDataLancamento().format(formatter)));

//--------------------------------------//--------------------------------------------------//--------------------------

        //CURSO ANTERIOR
//        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
//                .filter(episodio -> episodio.getAvaliacao() > 0.0)
//                .collect(Collectors.groupingBy(Episodio::getTemporada,
//                        Collectors.averagingDouble(Episodio::getAvaliacao)));
//        System.out.println(avaliacoesPorTemporada);
//
//        DoubleSummaryStatistics est = episodios.stream()
//                .filter(episodio -> episodio.getAvaliacao() > 0.0)
//                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
//        System.out.println("Media: " + est.getAverage());
//        System.out.println("Melhor avaliação: " + est.getMax());
//        System.out.println("Pior avaliação: " + est.getMin());
//        System.out.println("Quantidade: " + est.getCount());
//------------------------------------------//------------------------------------------------//------------------------
    }


    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        repository.save(serie);
        //dadosSeries.add(dados);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da Série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        //DadosSerie dadosSerie = getDadosSerie();
        listarSeriesBuscadas();
        System.out.println("Escolha uma serie pelo nome: ");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = repository.findByTituloContainingIgnoreCase(nomeSerie);
        //Optional<Serie> serie = series.stream().filter(s -> s.getTitulo().toLowerCase()
        //        .contains(nomeSerie.toLowerCase())).findFirst();

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporada(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().trim().toLowerCase().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream().flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            repository.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void listarSeriesBuscadas() {
        series = repository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
        //dadosSeries.forEach(System.out::println);
    }

    private void buscarSinopseSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        var sinopse = dadosSerie.sinopse();
        System.out.println("\n" + sinopse);

    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma serie pelo nome: ");
        var nomeSerie = leitura.nextLine();
        serieBusca = repository.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
            System.out.println("Dados da serie: " + serieBusca.get());
        } else {
            System.out.println("Serie não encontrada!");
        }
    }

    private void buscarSeriePorAtor() {
        System.out.println("Qual o nome para buscar: ");
        var nomeAtor = leitura.nextLine();
        System.out.println("Avaliação a partir de que valor? ");
        var avaliacao = leitura.nextDouble();
        List<Serie> seriesEncontradas = repository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
        System.out.println("Series em que " + nomeAtor + "trabalhou");
        seriesEncontradas.forEach(serie ->
                System.out.println(serie.getTitulo() + " avaliação: " + serie.getAvaliacao()));
    }

    private  void  buscarTop5Series(){
        List<Serie> serieTop = repository.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(serie ->
                System.out.println(serie.getTitulo() + " avaliação: " + serie.getAvaliacao()));;
    }

    private void buscarSeriePorCategoria(){
        System.out.println("Deseja buscar series de que categoria/gênero?");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesPorCategoria = repository.findByGenero(categoria);
        System.out.println("Series da categoria " + nomeGenero);
        seriesPorCategoria.forEach(System.out::println);
    }

    private void buscarSeriePorCategoriaTemporadaAvalicao(){
        System.out.println("Qual categoria deseja: ");
        var nomeCategoria = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeCategoria);


        System.out.println("Quantas temporadas: ");
        var totalTemporada = leitura.nextInt();

        System.out.println("Determine a nota de avalição: ");
        var notaAvaliacao = leitura.nextDouble();
        /*List<Serie> serieMaratona = repository.findByGeneroAndTotalTemporadaLessThanEqualAndAvaliacaoGreaterThanEqual(
                categoria, totalTemporada, notaAvaliacao);*/
        List<Serie> serieMaratona = repository.serieParaMaratonar(categoria, totalTemporada, notaAvaliacao);
        System.out.println("Resultado da sua maratona: ");
        serieMaratona.forEach(serie -> System.out.println("Serie: " + serie.getTitulo() +" \nGenero: " + serie.getGenero() +
                " \nTotal de temporadas: " + serie.getTotalTemporada() + " \nAvaliação: " + serie.getAvaliacao()));
    }

    private void buscarEpisodioPorTrecho(){
        System.out.println("Qual o nome do episódio para buscar: ");
        var trechoEpisodio = leitura.nextLine();
        List<Episodio> episodiosEncontrados = repository.episodioPorTrecho(trechoEpisodio);
        //episodiosEncontrados.forEach(System.out::println);
        episodiosEncontrados.forEach(e ->
                System.out.printf("Series: %s Temporada %s - Episodios %s - %s\n", e.getSerie()
                .getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()));
    }

    private void topEpisodiosPorSerie(){
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = repository.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(e ->
                    System.out.printf("Serie: %s Temporada %s - Episodio %s - %s Avaliação: %s\n",
                            e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(),
                            e.getTitulo(), e.getAvaliacao()));
        }
    }

    private void buscarEpisodiosDepoisDeUmaData(){
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            System.out.println("Digite o ano limite de lançamento");
            var anoLancamento = leitura.nextInt();
            leitura.nextLine();

            List<Episodio> episodiosAno = repository.episodioPorSerieEAno(serie, anoLancamento);
            episodiosAno.forEach(System.out::println);
        }
    }
}


