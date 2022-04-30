package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.dao.IRoomPriorityDao;
import com.epam.jwd.finaltask.dao.impl.RoomPriorityDaoImpl;
import com.epam.jwd.finaltask.model.RoomPriority;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CalculateRoomPriorityCommand extends AbstractCommand {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateRoomPriorityCommand.class);
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            request.setAttribute("error", "A file type is not multipart.");
            return Pages.ROOMPRIORITIES;
        }

        try {
            IRoomPriorityDao calculatePriorityDao = new RoomPriorityDaoImpl();
            calculatePriorityDao.recalculateRoomPriority(calculate(readFromFile(request)));
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An exception when uploading file with expert assessment.");
            return Pages.ROOMPRIORITIES;
        }
        request.setAttribute("error", "The file has been successfully uploaded and room priorities are recalculated.");
        return Pages.ROOMPRIORITIES;
    }

    /**
     * @param roomPriorityList - list of RoomPriority objects with all expert assessments
     * @return - list of RoomPriority objects with calculated priorities
     */
    private List<RoomPriority> calculate(List<RoomPriority> roomPriorityList) {
        final int NORMILIZER = 10;

        //Sum up all the assessments grouped by expert, guest type, room id
        Map<List<Integer>, RoomPriority> expertAssessments = roomPriorityList.stream()
                .collect(Collectors.toMap(
                        key -> Arrays.asList(key.getExpertId(), key.getGuesttypeId(), key.getRoomId()),
                        value -> value,
                        (e1, e2) -> {
                            e1.setPriority((e1.getPriority() + e2.getPriority()));
                            return e1;
                        },
                        LinkedHashMap::new));
        LOGGER.debug(expertAssessments.toString());

        //Normalize the expert assessment
        Map<List<Integer>, RoomPriority> expertAssessmentsNormalized = roomPriorityList.stream()
                .collect(Collectors.toMap(
                        f -> Arrays.asList(f.getExpertId(), f.getGuesttypeId(), f.getRoomId()),
                        e -> e,
                        (e1, e2) -> {
                            e1.setPriority((e1.getPriority() / NORMILIZER));
                            return e1;
                        },
                        LinkedHashMap::new));
        LOGGER.debug(expertAssessmentsNormalized.toString());

        //Sum up normalized assessment grouped by Guest type and Room id
        Map<List<Integer>, RoomPriority> weights = expertAssessmentsNormalized.values().stream()
                .collect(Collectors.toMap(
                        f -> Arrays.asList(f.getGuesttypeId(), f.getRoomId()),
                        e -> e,
                        (e1, e2) -> {
                            e1.setPriority((e1.getPriority() + e2.getPriority()));
                            return e1;
                        },
                        LinkedHashMap::new));
        LOGGER.debug(weights.toString());

        //Create a list from the Map
        List<RoomPriority> result =
                weights.entrySet()
                        .stream()
                        .map(e -> new RoomPriority(e.getKey().get(0), e.getValue().getExpertId(), e.getKey().get(1),
                                e.getValue().getPriority()))
                        .collect(Collectors.toList());
        return result;
    }

    /**
     * Parses the file and returns the list of RoomPriority objects
     *
     * @param request
     * @return
     */
    private List<RoomPriority> readFromFile(HttpServletRequest request) {

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored

        String uploadFolder = request.getServletContext().getRealPath("")
                + File.separator;

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        List<RoomPriority> roomPriorityList = new ArrayList<RoomPriority>();
        try {
            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadFolder + File.separator + fileName;
                    File uploadedFile = new File(filePath);
                    System.out.println(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);
                    final BufferedReader br = new BufferedReader(new FileReader(uploadedFile));
                    try {
                        String fileLine = br.readLine();
                        while ((fileLine = br.readLine()) != null) {
                            String[] splitedText = fileLine.split(";");
                            RoomPriority roomPriority = new RoomPriority();
                            roomPriority.setGuesttypeId(Integer.parseInt(splitedText[0]));
                            roomPriority.setExpertId(Integer.parseInt(splitedText[1]));
                            roomPriority.setRoomId(Integer.parseInt(splitedText[2]));
                            roomPriority.setPriority(Double.parseDouble(splitedText[4]));
                            roomPriorityList.add(roomPriority);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        br.close();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomPriorityList;
    }
}