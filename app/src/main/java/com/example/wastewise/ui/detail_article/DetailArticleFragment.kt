//package com.example.wastewise.ui.detail_article
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.wastewise.data.Result
//import androidx.fragment.app.viewModels
//import com.bumptech.glide.Glide
//import com.example.wastewise.databinding.FragmentDetailArticleBinding
//import com.example.wastewise.ui.detail__article.DetailArticleViewModel
//import com.example.wastewise.utils.ViewModelFactory
//
//class DetailArticleFragment : Fragment() {
//
//    private var _binding: FragmentDetailArticleBinding? = null
//    private val detailArticleViewModel by viewModels<DetailArticleViewModel> {
//        ViewModelFactory(requireContext())
//    }
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = FragmentDetailArticleBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val id = arguments?.getString(EXTRA_ID).toString()
//        val title = arguments?.getString(EXTRA_TITLE).toString()
//
//        detailArticleViewModel.getArticleDetail(id).observe(viewLifecycleOwner, {
//            if (it != null) {
//                when(it) {
//                    is Result.Success -> {
//                        showLoading(false)
////                        binding.titleArticle.text =
//                        Glide.with(this)
//                            .load(it.data.data[0].content[0].image)
//                            .into(binding.imageArticle)
//                        binding.paragraphArticle1.text = it.data.data[0].content[1].toString()
//                        binding.paragraphArticle2.text = it.data.data[0].content[2].toString()
//                        binding.paragraphArticle3.text = it.data.data[0].content[3].toString()
//                    }
//                    is Result.Loading -> {
//                        showLoading(true)
//                    }
//                    is Result.Error -> {
//                        showLoading(false)
//                    }
//                }
//            } else {
//                showLoading(false)
//            }
//        })
//
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    private fun showLoading(isLoading: Boolean) {
//        binding.progressIndicatorDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
//    }
//
//    companion object {
//        const val EXTRA_ID = "extra_id"
//        const val EXTRA_TITLE = "extra_title"
//    }
//}