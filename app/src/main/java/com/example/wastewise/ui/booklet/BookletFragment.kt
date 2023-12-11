package com.example.wastewise.ui.booklet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wastewise.data.Result
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wastewise.data.remote.response.booklet.Booklet
import com.example.wastewise.databinding.FragmentBookletBinding
import com.example.wastewise.ui.detail_booklet.DetailBookletActivity
import com.example.wastewise.ui.profile.ProfileViewModel
import com.example.wastewise.utils.ViewModelFactory

class BookletFragment : Fragment() {

    private var _binding: FragmentBookletBinding? = null
    private val bookletViewModel: BookletViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBookletBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutManager = LinearLayoutManager(requireContext())

        with(binding) {
            rvBooklet.layoutManager = layoutManager
            rvBooklet.setHasFixedSize(true)
            getData()
        }

        return root
    }

    private fun getData() {

        bookletViewModel.getBooklet().observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Result.Success -> {
                        showLoading(false)
                        setListData(it.data.data)
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Loading -> {
                        showLoading(true)
                    }
                }
            }
        })

    }

    private fun setListData(booklet: List<Booklet>) {
        val adapter = BookletAdapter()
        adapter.submitList(booklet)
        binding.rvBooklet.adapter = adapter
    }

    private fun clickItem(booklet: Booklet) {
        val adapter = BookletAdapter()
        adapter.setOnItemClickCallback(object : BookletAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Booklet) {
                val intent = Intent(context, DetailBookletActivity::class.java)
                intent.putExtra(DetailBookletActivity.EXTRA_ID, data.id)
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicatorBooklet.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}