package mx.com.edlosproyect.ui.facts.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import mx.com.edlosproyect.R
import mx.com.edlosproyect.data.local.model.ResultsModel
import mx.com.edlosproyect.databinding.FragmentHomeBinding
import mx.com.edlosproyect.sys.utils.OnClickResult
import mx.com.edlosproyect.ui.facts.home.pageadapter.PageAdapter


@AndroidEntryPoint
class HomeFragment : Fragment(), OnClickResult {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapter: PageAdapter
    var list = arrayListOf<ResultsModel>()
    private var fromId=false
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun init() {
        viewModel.getPage()
        viewModel.factsLiveData.observe(viewLifecycleOwner, this::setResults)
    }


    private fun setResults(results: List<ResultsModel>) {
        if (list.size == results.size && !fromId) {
            Toast.makeText(context, "No hay mas datos", Toast.LENGTH_SHORT).show()
            binding.progressCircular.visibility = View.GONE
        } else {
            val list = arrayListOf<ResultsModel>()
            for (r in results)
                list.add(r)
            this.list = list
            this.adapter = PageAdapter(list, this)
            binding.recyclerPage.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.recyclerPage.adapter = adapter
            binding.scroll.visibility = View.VISIBLE
            binding.loadingPage.visibility = View.GONE

            binding.scroll.setOnScrollChangeListener(object :
                NestedScrollView.OnScrollChangeListener {
                override fun onScrollChange(
                    v: NestedScrollView,
                    scrollX: Int,
                    scrollY: Int,
                    oldScrollX: Int,
                    oldScrollY: Int
                ) {
                    if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight)
                        viewModel.getPage()
                    binding.progressCircular.visibility = View.VISIBLE
                }
            })
        }
        fromId=false
    }

    override fun setOnClickListener(result: ResultsModel) {
        var bundle = Bundle()
        bundle.putString("ID", result.Id)
        findNavController().navigate(R.id.action_navHome_to_info_fragment, bundle)
        fromId=true
    }
}