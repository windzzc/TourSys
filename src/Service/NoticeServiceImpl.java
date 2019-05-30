package Service;

import Dao.NoticeDao;
import Dao.NoticeDaoImpl;
import mapper.Notice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao = new NoticeDaoImpl();
    @Override
    public boolean addNotice(String content) {
        if(content==null) return false;
        Notice notice = new Notice();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        notice.setContent(content);
        notice.setCtime(date);
        return noticeDao.add(notice);
    }

    @Override
    public boolean deleteNotice(int id) {
        if(!noticeDao.findById(id)) return false;
        noticeDao.delete(id);
        return true;
    }

    @Override
    public String[][] getAll() {
        List<Notice> list = noticeDao.findAll();
        int size = list.size();
        String[][] res = new String[size][3];
        for(int i=0;i<size;i++){
            res[i][0]=String.valueOf(list.get(i).getNotice_id());
            res[i][1]=list.get(i).getContent();
            res[i][2]=list.get(i).getCtime();
        }
        return res;
    }
}
