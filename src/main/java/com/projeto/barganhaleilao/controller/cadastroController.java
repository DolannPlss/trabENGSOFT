package com.projeto.barganhaleilao.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.barganhaleilao.model.CadProduto;
import com.projeto.barganhaleilao.repository.Produtos;

@Controller
@RequestMapping("/cadastroObjeto")
public class cadastroController {

	@Autowired
	private Produtos produtos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroObjeto");
		mv.addObject(new CadProduto());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated CadProduto cadProduto, @RequestParam ("imagem") MultipartFile file,
								Errors erros) {
		ModelAndView mv =new ModelAndView("CadastroObjeto");
		if(erros.hasErrors()) {
			return mv;
		}
		try {
			FileUploadUtil.saveFile("C:\\Users\\marcos\\OneDrive\\Área de Trabalho\\tccMarcos1511\\src\\main\\resources\\static\\images\\upload",file.getOriginalFilename(),file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cadProduto.setCaminho("/images/upload/"+file.getOriginalFilename());
		produtos.save(cadProduto);
		mv.addObject("mensagem", "Produto salvo com sucesso!");

		return mv;
	}

}
