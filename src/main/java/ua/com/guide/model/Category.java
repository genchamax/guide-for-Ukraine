package ua.com.guide.model;

import javax.persistence.*;

/**
 * Created by Max on 08.08.2016.
 */
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false)
    private String categoryName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    //    @OneToMany
//    private List<Post> posts;
    // TODO: 08.08.2016 check if correct
}
