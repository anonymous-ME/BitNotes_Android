package in.bitnotes.affan.bitnotes.api;

import java.util.List;

import in.bitnotes.affan.bitnotes.model.Assignment;
import in.bitnotes.affan.bitnotes.model.ExamNote;
import in.bitnotes.affan.bitnotes.model.Note;
import in.bitnotes.affan.bitnotes.model.Practical;
import in.bitnotes.affan.bitnotes.model.QPaper;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by betterclever on 2/23/2017.
 */

public interface BitnotesService {
    
    @GET("examnotes")
    Call<List<ExamNote>> getExamNotes();
    
    @GET("assignments")
    Call<List<Assignment>> getAssignments();
    
    @GET("practicals")
    Call<List<Practical>> getPracticals();
    
    @GET("qpapers")
    Call<List<QPaper>> getQPapers();
    
    
}
