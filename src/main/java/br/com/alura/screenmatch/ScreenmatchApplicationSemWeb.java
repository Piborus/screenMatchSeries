//package br.com.alura.screenmatch.service;
//
//import br.com.alura.screenmatch.principal.Principal;
//import br.com.alura.screenmatch.repository.SerieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenmatchApplicationSemWeb implements CommandLineRunner {
//
//    @Autowired
//    private SerieRepository repository;
//
//    public static void main(String[] args) {
//        SpringApplication.run(ScreenmatchApplicationSemWeb.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Principal principal = new Principal(repository);
//        principal.exibirMenu();
//
////		var json1 = consumoAPI.obterDados("https://api.waifu.pics/sfw/waifu");
////		System.out.println(json1);
//
//
//    }
//}
