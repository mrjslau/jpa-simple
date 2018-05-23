package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Author;
import org.mybatis.cdi.Mapper;

@Mapper
public interface AuthorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Mar 20 00:06:51 EET 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Mar 20 00:06:51 EET 2018
     */
    int insert(Author record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Mar 20 00:06:51 EET 2018
     */
    Author selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Mar 20 00:06:51 EET 2018
     */
    List<Author> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Mar 20 00:06:51 EET 2018
     */
    int updateByPrimaryKey(Author record);
}