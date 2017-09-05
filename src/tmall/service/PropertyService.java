package tmall.service;

import java.util.List;

import tmall.pojo.Category;
import tmall.util.Page;

public interface PropertyService extends BaseService{
    public List listByCategory(Category category);
    public List list(Page page,Category category);
    public int total (Category category);
}
