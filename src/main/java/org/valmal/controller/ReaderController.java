package org.valmal.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.valmal.bean.Reader;
import org.valmal.service.BookService;
import org.valmal.service.ReaderService;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;
    @Autowired
    BookService bookService;

//    @RequestMapping("/load")
//    @ResponseBody
//    public String loadReaders() {
//
//        return readerService.readersToString(readerService.getReaders());
//    }

//    @RequestMapping("/find")
//    @ResponseBody
//    public String findReaders(
//            @RequestParam("id") String id,
//            @RequestParam("fName") String fName,
//            @RequestParam("mName") String mName,
//            @RequestParam("lName") String lName,
//            @RequestParam("phone") String phone,
//            HttpServletRequest request
//    ) {
//        List<Reader> readers = null;
//        Enumeration<String> params = request.getParameterNames();
//
//        for(Enumeration<String> p = params; p.hasMoreElements(); ){
//            switch (p.nextElement()){
//                case "id" : {
//                    if(!id.isEmpty()){
//                        readers = new ArrayList<>();
//                        readers.add(readerService.findReaderById(Integer.parseInt(id)));
//                    }
//                } break;
//                case "fName" : {
//                    if(!fName.isEmpty()){
//                        if(readers == null){
//                            readers = new ArrayList<>();
//                            readers.addAll(readerService.findReadersByFName(fName));
//                        } else {
//                            readers.removeIf(r -> !r.getfName().contains(fName));
//                        }
//                    }
//                } break;
//                case "mName" : {
//                    if(!mName.isEmpty()){
//                        if(readers == null){
//                            readers = new ArrayList<>();
//                            readers.addAll(readerService.findReadersByMName(mName));
//                        } else {
//                            readers.removeIf(r -> !r.getmName().contains(mName));
//                        }
//                    }
//                } break;
//                case "lName" : {
//                    if(!lName.isEmpty()){
//                        if(readers == null){
//                            readers = new ArrayList<>();
//                            readers.addAll(readerService.findReadersByLName(lName));
//                        } else {
//                            readers.removeIf(r -> !r.getlName().contains(lName));
//                        }
//                    }
//                } break;
//                case "phone" : {
//                    if(!phone.isEmpty()){
//                        if(readers == null){
//                            readers = new ArrayList<>();
//                            readers.addAll(readerService.findReadersByPhone(phone));
//                        } else {
//                            readers.removeIf(r -> !r.getPhone().contains(phone));
//                        }
//                    }
//                } break;
//            }
//        }
//
//        if(readers == null){ readers = new ArrayList<>(); }
//
//        return readerService.readersToString(readers);
//    }

    @RequestMapping(value = "/findByExample", method = RequestMethod.POST)
    @ResponseBody
    public String findByExample(@RequestBody String example) throws IOException {
        Reader ex = new ObjectMapper().readValue(example, Reader.class);
        return new ObjectMapper().writeValueAsString(readerService.findReadersByExample(ex));
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addReader(@RequestBody String obj
    ) throws IOException {
        Reader reader = new ObjectMapper().readValue(obj, Reader.class);
        reader.setRegistrationDate(new Date());
        readerService.insert(reader);
        return "reader added";
    }

    @RequestMapping("/findReaderJSON")
    @ResponseBody
    public String findJSONbyId(@RequestParam("id") int id) throws IOException, ParseException {
        Reader reader = readerService.findReaderById(id);
        return new ObjectMapper().writeValueAsString(reader);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(@RequestParam("id") int id) throws IOException {
        readerService.delete(readerService.findReaderById(id));
        return "reader removed";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody String json
    ) throws IOException {

        Reader reader = new ObjectMapper().readValue(json, Reader.class);
        reader.setRegistrationDate(readerService.findReaderById(reader.getId()).getRegistrationDate());

        readerService.update(reader);

        return "reader edited";
    }
}
