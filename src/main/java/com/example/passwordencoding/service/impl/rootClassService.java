package com.example.passwordencoding.service.impl;

import com.example.passwordencoding.model.RestData;
import com.example.passwordencoding.model.RootClass;
import com.example.passwordencoding.repository.RestDataRepository;
import com.example.passwordencoding.repository.RootClassRepository;
import com.example.passwordencoding.service.IRootClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class rootClassService implements IRootClassService {
    @Autowired
    private RootClassRepository rootClassRepository;

    @Autowired
    private RestDataRepository restDataRepository;

    @Autowired
    private org.springframework.web.client.RestTemplate restTemplate;

    String url = "https://jsonplaceholder.typicode.com/posts";

    public Object saveData() {
        try {
            ResponseEntity<RootClass[]> responseEntity = restTemplate.getForEntity(url, RootClass[].class);
            RootClass[] roots = responseEntity.getBody();

            if (roots != null && roots.length > 0) {
                for (RootClass root : roots) {
                    System.out.println(root);
                    RootClass rootClass = new RootClass();
                    rootClass.setId(root.getId());
                    rootClass.setBody(root.getBody());
                    rootClass.setTitle(root.getTitle());
                    rootClass.setUserId(root.getUserId());
                    rootClassRepository.save(rootClass);
                }
                return "Data saved";
            } else {
                return "No data to save";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to save data: " + e.getMessage();
        }
    }

    @Override
    public Object saveDataEntity() throws Exception {

        String urlApi="https://api-brfood.codesrv.com:2002/api/wishlistCart/getAllCart?pageNo=0&pageSize=10&userId=12725";
        ResponseEntity<RestData[]>restDataResponseEntity=restTemplate.getForEntity(urlApi,RestData[].class);

        RestData[] body = restDataResponseEntity.getBody();

        if (body!=null&& body.length>0){
            for (RestData restData:body){
                RestData restDataObj=new RestData();
                restDataObj.setCartId(restData.getCartId());
                restDataObj.setImage(restData.getImage());
                restDataObj.setPrice(restData.getPrice());
                restDataObj.setProductId(restData.getProductId());
                restDataObj.setProductTitle(restData.getProductTitle());
                restDataObj.setQuantity(restData.getQuantity());

               restDataRepository.save(restDataObj);

            }
        }
           throw  new Exception("body not present");
    }

}
