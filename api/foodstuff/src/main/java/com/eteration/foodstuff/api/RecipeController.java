package com.eteration.foodstuff.api;


import com.eteration.foodstuff.request.RecipeRequest;
import com.eteration.foodstuff.response.RecipeListResponse;
import com.eteration.foodstuff.response.RecipeResponse;
import com.eteration.foodstuff.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/recipes/")
public class RecipeController {
    private final RecipeService recipeService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<RecipeListResponse> getRecipes() {
        try {
            RecipeListResponse res = new RecipeListResponse();
            res.setRecipeList(recipeService.getList());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<RecipeResponse> getRecipe(@PathVariable(name = "id") long id) {
        try {
            RecipeResponse res = new RecipeResponse();
            res.setRecipeDto(recipeService.getRecipe(id));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<RecipeResponse> addRecipe(@RequestBody RecipeRequest req) {
        try {
            RecipeResponse res = new RecipeResponse();
            res.setRecipeDto(recipeService.addRecipe(req.getRecipeDto()));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteRecipe(@PathVariable(name = "id") long id) {
        try {
            recipeService.removeRecipe(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @CrossOrigin
    @GetMapping("/user/{userId}")
    public ResponseEntity<RecipeListResponse> getRecipesByUserId(@PathVariable(name = "userId") long userId) {
        try {
            RecipeListResponse res = new RecipeListResponse();
            res.setRecipeList(recipeService.getListByUserId(userId));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("search/{title}")
    public ResponseEntity<RecipeListResponse> getListByTitle(@PathVariable(name = "title") String title ) {
        try {
            RecipeListResponse res = new RecipeListResponse();
            res.setRecipeList(recipeService.getListByContainingTitle(title));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}