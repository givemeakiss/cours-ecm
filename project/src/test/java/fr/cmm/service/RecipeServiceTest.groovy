package fr.cmm.service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import static fr.cmm.SpringProfiles.INTEG;
import static java.util.Arrays.asList;
import static java.util.stream.StreamSupport.stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImageServiceTestConfig.class)
@ActiveProfiles(INTEG)
public class RecipeServiceTest {
    @Inject
    private RecipeService recipeService;

    @Inject
    private MongoCollection recipeCollection;

    @Before
    @After
    public void clean() {
        recipeCollection.remove();
    }

   /*@Test
    public void save() {
        Recipe recipe = new Recipe();
        recipe.setTitle("test recipe");

        recipeService.save(recipe);

        Assert.assertEquals("test recipe", recipeCollection.findOne().as(Recipe.class).getTitle());
    }*/

    @Test
    void save() {
        recipeService.save(new Recipe(title: 'test recipe'))

        assert recipeCollection.findOne().as(Recipe).title == 'test recipe'
    }

    @Test
    void findById() {
        Recipe recipe = new Recipe(title : "test recipe")
        //recipe.setTitle("test recipe");

        recipeService.save(recipe);

        //Assert.assertEquals("test recipe", recipeService.findById(recipe.getId()).getTitle());
        assert "test recipe" == recipeService.findById(recipe.getId()).getTitle()
    }

    @Test
    void findByQuery() {
        /*recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());*/
        5.times { recipeService.save(new Recipe()) }

        //Assert.assertEquals(5, stream(recipeService.findByQuery(new PageQuery()).spliterator(), false).count());
        assert recipeService.findByQuery(new PageQuery()).size() == 5;

    }

    @Test
    void findByQueryWithCustomPageSize() {
        /*recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());
        recipeService.save(new Recipe());*/

        5.times { recipeService.save(new Recipe()) }

        PageQuery pageQuery = new PageQuery(size : 2);
        //pageQuery.setSize(2);

        //Assert.assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
        assert recipeService.findByQuery(pageQuery).size() == 2;
    }

    @Test
    void findByQueryWithTag() {
        /*recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag1"));
        recipeService.save(new Recipe().withTags("tag2"));
        recipeService.save(new Recipe().withTags("tag2"));*/
        recipeService.save(new Recipe().withTags("tag3"));
        2.times { recipeService.save(new Recipe().withTags("tag1")) }
        2.times { recipeService.save(new Recipe().withTags("tag2")) }

        PageQuery pageQuery = new PageQuery(tag: "tag1");
        //pageQuery.setTag("tag1");

        //Assert.assertEquals(2, stream(recipeService.findByQuery(pageQuery).spliterator(), false).count());
        assert recipeService.findByQuery(pageQuery).size() == 2;


    }

    @Test
    void findAllTags() {
        new Recipe(tags: ['tag1', 'tag2'])
        recipeService.save(new Recipe(tags: ['tag1', 'tag2']));
        recipeService.save(new Recipe(tags: ['tag2', 'tag3']));

        //Assert.assertEquals(asList("tag1", "tag2", "tag3"), recipeService.findAllTags());
        assert asList("tag1", "tag2", "tag3") == recipeService.findAllTags()
    }

    @Test
    void 'findById with invalid id'() {
        //Assert.assertEquals(recipeService.findById("bade"), null);
        assert recipeService.findById("bad") == null
    }

    @Test
    void countByQuery() {
        PageQuery pageQuery = new PageQuery(tag: "choucroute");
        //pageQuery.setTag("choucroute");

        //Assert.assertEquals(0, recipeService.countByQuery(pageQuery));
        assert recipeService.countByQuery(pageQuery) == 0
    }
}