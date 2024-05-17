package com.example.passwordencoding.service.impl;


import com.example.passwordencoding.model.Result;
import com.example.passwordencoding.repository.JesonRepository;
import com.example.passwordencoding.service.IjesonServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional
public class JesonService implements IjesonServise {


    @Autowired
    private JesonRepository jesonRepository;

    @Autowired
    private RestTemplate restTemplate;

   // private String url = "https://randomuser.me/api/?results=5&inc=name,location,email,phone,cell,picture,registered,id,login,dob";
  /*
    @Override
    public Object saveOrUpdate(RootClass rootClass) {
        /*
        try {
          /*  RootClass rootClass = new RootClass();
            rootClass.setId(1);
            rootClass.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
            rootClass.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
            rootClass.setUserId(1);



            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
            requestBody.put("body", "quia et suscipit\n" +
                    "suscipit recusandae consequuntur expedita et cum\n" +
                    "reprehenderit molestiae ut ut quas totam\n" +
                    "nostrum rerum est autem sunt rem eveniet architecto");
            requestBody.put("id", "1");

            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<RootClass> responseEntity = restTemplate.postForEntity(url, requestEntity, RootClass.class);
            RootClass result = responseEntity.getBody();
            //  Result result1 = restTemplate.postForObject(API_URL, rootClass, Result.class);
            if (result != null) {
             //   jesonRepository.save(result);
                return "save";
            } else {
                return "not to save";
            }
        } catch (HttpStatusCodeException e) {
            String responseBody = e.getResponseBodyAsString();
            return "HTTP Error: " + responseBody;
        } catch (Exception e) {

            return "An error occurred: " + e.getMessage();
        }
    }
    */

        /*
       try{

        RestTemplate restTemplate=new RestTemplate();
        Result result=new Result();
        restTemplate.exchange(API_URL,HttpMethod.POST,result,Result.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(result, headers);

        ResponseEntity<Result> responseEntity = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, Result.class);

        return responseEntity;

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

         */
        /*
        try {
              Result result=new Result();
              result.setDob(result.getDob());
              result.setCell(result.getCell());
              result.setEmail(result.getEmail());
              result.setId(result.getId());
              result.setLocation(result.getLocation());
              result.setLogin(result.getLogin());
              result.setName(result.getName());
              result.setPhone(result.getPhone());
              result.setPicture(result.getPicture());
              result.setRegistered(result.getRegistered());

         //   String apiUrl = "https://randomuser.me/api/?results=5&inc=name,location,email,phone,cell,picture,registered,id,login,dob,company";

            ResponseEntity<Result> results = restTemplate.exchange(API_URL,HttpMethod.POST,Result.class);
            System.out.println("Data saved: " + results);
            if (results != null) {

               // jesonRepository.save(results);

                return "Data saved";
            } else {
                return "No data to save";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to save dat  a";
        }
    }

         return null;
    }
    */


    @Override
    public Object saveOrUpdate() {
        try {
            String url = "https://randomuser.me/api/?results=5&inc=name,location,email,phone,cell,picture,registered,id,login,dob,company";

            ResponseEntity<Result[]> responseEntity = restTemplate.getForEntity(url, Result[].class);
            Result[] result = responseEntity.getBody();

            if (result != null && result.length > 0) {
                for (Result results : result) {
                    Result resultObj = new Result();
                    resultObj.setCell(results.getCell());
                    resultObj.setEmail(results.getEmail());
                    resultObj.setPhone(results.getPhone());

                 /*
                    resultObj.setDob(results.getDob());
                    resultObj.setId(results.getId());
                    resultObj.setLocation(results.getLocation());
                    resultObj.setLogin(results.getLogin());
                    resultObj.setName(results.getName());
                    resultObj.setPicture(results.getPicture());
                    resultObj.setRegistered(results.getRegistered());

                  */

                    jesonRepository.save(resultObj);
                }
                return "data saved";
            } else {
                return "data not saved";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
