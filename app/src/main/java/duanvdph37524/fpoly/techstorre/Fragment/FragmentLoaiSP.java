package duanvdph37524.fpoly.techstorre.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import duanvdph37524.fpoly.techstorre.Adapter.AdapterLoaiSanPham;
import duanvdph37524.fpoly.techstorre.DAO.LoaiSanPhamDAO;
import duanvdph37524.fpoly.techstorre.R;
import duanvdph37524.fpoly.techstorre.model.LoaiSanPham;


public class FragmentLoaiSP extends Fragment {
    RecyclerView rcvLoaiSP;
     AdapterLoaiSanPham adapterLoaiSanPham;
    LoaiSanPhamDAO loaiSanPhamDAO;
    ArrayList<LoaiSanPham> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_loai_s_p, container, false);
        rcvLoaiSP = view.findViewById(R.id.rcv_LoaiSP);
        loaiSanPhamDAO = new LoaiSanPhamDAO(getActivity());
        list = loaiSanPhamDAO.getALL();
        adapterLoaiSanPham = new AdapterLoaiSanPham(getActivity(),list);
        rcvLoaiSP.setAdapter(adapterLoaiSanPham);
        rcvLoaiSP.setLayoutManager(new GridLayoutManager(getActivity(),3));
        return view;
    }
}